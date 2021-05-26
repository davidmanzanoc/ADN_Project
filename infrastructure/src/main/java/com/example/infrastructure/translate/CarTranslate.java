package com.example.infrastructure.translate;

import com.example.domain.model.Car;
import com.example.infrastructure.database.entity.CarEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CarTranslate {

    public static CarEntity translateCarFromDomainToDB(Car car) {
        CarEntity carEntity = new CarEntity();
        carEntity.setLicensePlate(car.getLicensePlate());
        carEntity.setEntryDate(car.getEntryDate().toString());
        return carEntity;
    }

    public static Car translateCarFromDBToDomain(CarEntity carEntity) {
        LocalDateTime localDateTime = LocalDateTime.parse(carEntity.entryDate);
        return new Car(carEntity.licensePlate, localDateTime);
    }

    public static List<Car> translateCarListFromDBToDomain(List<CarEntity> carList) {
        List<Car> translatedCarList = new ArrayList<>();
        for (CarEntity carEntity : carList) {
            translatedCarList.add(translateCarFromDBToDomain(carEntity));
        }
        return translatedCarList;
    }
}
