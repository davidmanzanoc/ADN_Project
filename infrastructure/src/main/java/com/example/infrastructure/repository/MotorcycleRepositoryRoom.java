package com.example.infrastructure.repository;

import android.content.Context;

import com.example.domain.model.Motorcycle;
import com.example.domain.repository.MotorcycleRepository;
import com.example.infrastructure.database.ParkingDatabase;
import com.example.infrastructure.database.entity.MotorcycleEntity;
import com.example.infrastructure.threads.motorcycle.GetMotorcyclesThread;
import com.example.infrastructure.threads.motorcycle.GetNumberOfMotorcyclesThread;
import com.example.infrastructure.translate.MotorcycleTranslate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
        ParkingDatabase.EXECUTOR_SERVICE.execute(() ->
                parkingDatabase.motorcycleDao().saveMotorcycle(motorcycleEntity));
    }

    @Override
    public void deleteMotorcycle(Motorcycle motorcycle) {
        MotorcycleEntity motorcycleEntity = MotorcycleTranslate.translateMotorcycleFromDomainToDB(motorcycle);
        ParkingDatabase.EXECUTOR_SERVICE.execute(()->
                parkingDatabase.motorcycleDao().deleteMotorcycle(motorcycleEntity.licensePlate));
    }

    @Override
    public int getNumberOfMotorcycles() {
        int numberOfMotorcycles = 0;
        GetNumberOfMotorcyclesThread getNumberOfMotorcyclesThread = new GetNumberOfMotorcyclesThread(parkingDatabase);
        try {
            numberOfMotorcycles = getNumberOfMotorcyclesThread.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return numberOfMotorcycles;
    }

    @Override
    public List<Motorcycle> getMotorcycles() {
        List<Motorcycle> motorcycleList = new ArrayList<>();
        GetMotorcyclesThread getMotorcyclesThread = new GetMotorcyclesThread(parkingDatabase);
        try {
            List<MotorcycleEntity> motorcycleEntityList = getMotorcyclesThread.execute().get();
            motorcycleList.addAll(MotorcycleTranslate.translateMotorcycleListFromDBToDomain(motorcycleEntityList));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return motorcycleList;
    }
}
