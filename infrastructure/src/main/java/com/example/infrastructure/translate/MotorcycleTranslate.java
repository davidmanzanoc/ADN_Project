package com.example.infrastructure.translate;

import com.example.domain.model.Motorcycle;
import com.example.infrastructure.database.entity.MotorcycleEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MotorcycleTranslate {

    public static MotorcycleEntity translateMotorcycleFromDomainToDB(Motorcycle motorcycle) {
        MotorcycleEntity motorcycleEntity = new MotorcycleEntity();
        motorcycleEntity.setLicensePlate(motorcycle.getLicensePlate());
        motorcycleEntity.setEntryDate(motorcycle.getEntryDate().toString());
        motorcycleEntity.setCylinderCapacity(motorcycle.getCylinderCapacity());
        return motorcycleEntity;
    }

    public static Motorcycle translateMotorcycleFromDBToDomain(MotorcycleEntity motorcycleEntity) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime entryDateFormatted = LocalDateTime.parse(motorcycleEntity.entryDate, dateTimeFormatter);
        return new Motorcycle(motorcycleEntity.licensePlate, entryDateFormatted, motorcycleEntity.cylinderCapacity);
    }

    public static List<Motorcycle> translateMotorcycleListFromDBToDomain(List<MotorcycleEntity> motorcycleList) {
        List<Motorcycle> translatedMotorcycleList = new ArrayList<>();
        for (MotorcycleEntity motorcycleEntity : motorcycleList) {
            translatedMotorcycleList.add(translateMotorcycleFromDBToDomain(motorcycleEntity));
        }
        return translatedMotorcycleList;
    }
}
