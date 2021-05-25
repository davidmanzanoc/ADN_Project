package com.example.infrastructure.threads.motorcycle;

import android.os.AsyncTask;

import com.example.infrastructure.database.ParkingDatabase;

public class GetNumberOfMotorcycles extends AsyncTask<Void, Void, Integer> {

    private ParkingDatabase parkingDatabase;

    public GetNumberOfMotorcycles(ParkingDatabase parkingDatabase) {
        this.parkingDatabase = parkingDatabase;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return parkingDatabase.motorcycleDao().getNumberOfMotorcycles();
    }
}
