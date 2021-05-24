package com.example.infrastructure.repository;

import android.content.Context;

import com.example.domain.model.Motorcycle;
import com.example.domain.repository.MotorcycleRepository;
import com.example.infrastructure.database.ParkingDatabase;
import com.example.infrastructure.database.entity.CarEntity;
import com.example.infrastructure.database.entity.MotorcycleEntity;
import com.example.infrastructure.translate.MotorcycleTranslate;

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

    }

    @Override
    public void deleteMotorcycle(Motorcycle motorcycle) {

    }

    @Override
    public int getNumberOfMotorcycles() {
        return 0;
    }

    @Override
    public List<Motorcycle> getMotorcycles() {
        return null;
    }
}
