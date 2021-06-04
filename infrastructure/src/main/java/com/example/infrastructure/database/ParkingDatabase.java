package com.example.infrastructure.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.infrastructure.car.database.dao.CarDao;
import com.example.infrastructure.motorcycle.database.dao.MotorcycleDao;
import com.example.infrastructure.car.database.entity.CarEntity;
import com.example.infrastructure.motorcycle.database.entity.MotorcycleEntity;

@Database(entities = {CarEntity.class, MotorcycleEntity.class}, version = 1, exportSchema = false)
public abstract class ParkingDatabase extends RoomDatabase {

    public abstract CarDao carDao();

    public abstract MotorcycleDao motorcycleDao();

    private static ParkingDatabase databaseInstance = null;

    public static ParkingDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.
                    databaseBuilder(context, ParkingDatabase.class, "parking_database")
                    .build();
        }
        return databaseInstance;
    }
}
