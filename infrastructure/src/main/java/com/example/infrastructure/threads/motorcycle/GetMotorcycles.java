package com.example.infrastructure.threads.motorcycle;

import android.os.AsyncTask;

import com.example.infrastructure.database.ParkingDatabase;
import com.example.infrastructure.database.entity.MotorcycleEntity;

import java.util.List;

public class GetMotorcycles extends AsyncTask<Void, Void, List<MotorcycleEntity>> {

    private ParkingDatabase parkingDatabase;

    public GetMotorcycles(ParkingDatabase parkingDatabase) {
        this.parkingDatabase = parkingDatabase;
    }

    @Override
    protected List<MotorcycleEntity> doInBackground(Void... voids) {
        return parkingDatabase.motorcycleDao().getMotorcycles();
    }
}
