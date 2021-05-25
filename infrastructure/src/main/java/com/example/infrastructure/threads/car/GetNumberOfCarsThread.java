package com.example.infrastructure.threads.car;

import android.os.AsyncTask;

import com.example.infrastructure.database.ParkingDatabase;

public class GetNumberOfCarsThread extends AsyncTask<Void, Void, Integer> {

    private ParkingDatabase parkingDatabase;

    public GetNumberOfCarsThread(ParkingDatabase parkingDatabase) {
        this.parkingDatabase = parkingDatabase;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return parkingDatabase.carDao().getNumberOfCars();
    }
}
