package com.example.infrastructure.motorcycle.repository;

import android.content.Context;

import com.example.domain.parking.exception.GlobalException;
import com.example.domain.vehicle.motorcycle.model.Motorcycle;
import com.example.domain.vehicle.motorcycle.repository.MotorcycleRepository;
import com.example.infrastructure.database.ParkingDatabase;
import com.example.infrastructure.motorcycle.database.entity.MotorcycleEntity;
import com.example.infrastructure.motorcycle.translate.MotorcycleTranslate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class MotorcycleRepositoryRoom implements MotorcycleRepository {

    private ParkingDatabase parkingDatabase;

    @Inject
    public MotorcycleRepositoryRoom(@ApplicationContext Context context) {
        parkingDatabase = ParkingDatabase.getInstance(context);
    }

    @Override
    public void saveMotorcycle(Motorcycle motorcycle) {
        MotorcycleEntity motorcycleEntity = MotorcycleTranslate.translateMotorcycleFromDomainToDB(motorcycle);
        parkingDatabase.motorcycleDao().saveMotorcycle(motorcycleEntity);
    }

    @Override
    public void deleteMotorcycle(Motorcycle motorcycle) {
        MotorcycleEntity motorcycleEntity = MotorcycleTranslate.translateMotorcycleFromDomainToDB(motorcycle);
        parkingDatabase.motorcycleDao().deleteMotorcycle(motorcycleEntity.getLicensePlate());
    }

    @Override
    public int getNumberOfMotorcycles() {
        int numberOfMotorcycles;
        try {
            numberOfMotorcycles = parkingDatabase.motorcycleDao().getNumberOfMotorcycles();
        } catch (Exception e) {
            throw new GlobalException("Error al obtener la cantidad de motos", e);
        }
        return numberOfMotorcycles;
    }

    @Override
    public List<Motorcycle> getMotorcycles() {
        List<Motorcycle> motorcycleList = new ArrayList<>();
        try {
            List<MotorcycleEntity> motorcycleEntityList = parkingDatabase.motorcycleDao().getMotorcycles();
            motorcycleList.addAll(MotorcycleTranslate.translateMotorcycleListFromDBToDomain(motorcycleEntityList));
        } catch (Exception e) {
            throw new GlobalException("Error al obtener la lista de motos", e);
        }
        return motorcycleList;
    }
}
