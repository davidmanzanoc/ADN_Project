package com.example.infrastructure.motorcycle.threads;

import android.os.AsyncTask;

import com.example.infrastructure.database.ParkingDatabase;
import com.example.infrastructure.motorcycle.database.entity.MotorcycleEntity;

import java.util.List;

public class GetMotorcyclesThread extends AsyncTask<Void, Void, List<MotorcycleEntity>> {

    private ParkingDatabase parkingDatabase;

    public GetMotorcyclesThread(ParkingDatabase parkingDatabase) {
        this.parkingDatabase = parkingDatabase;
    }

    @Override
    protected List<MotorcycleEntity> doInBackground(Void... voids) {
        return parkingDatabase.motorcycleDao().getMotorcycles();
    }
}