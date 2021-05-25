package com.example.infrastructure.threads.car;

import android.os.AsyncTask;

import com.example.infrastructure.database.ParkingDatabase;
import com.example.infrastructure.database.entity.CarEntity;

import java.util.List;

public class GetCarsThread extends AsyncTask<Void, Void, List<CarEntity>> {

    private ParkingDatabase parkingDatabase;

    public GetCarsThread(ParkingDatabase parkingDatabase) {
        this.parkingDatabase = parkingDatabase;
    }

    @Override
    protected List<CarEntity> doInBackground(Void... voids) {
        return parkingDatabase.carDao().getCars();
    }
}
