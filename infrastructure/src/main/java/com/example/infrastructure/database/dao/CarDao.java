package com.example.infrastructure.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infrastructure.database.entity.CarEntity;

import java.util.List;

@Dao
public interface CarDao {

    @Insert
    void saveCar(CarEntity carEntity);

    @Query("SELECT * FROM car")
    List<CarEntity> getCars();

    @Query("DELETE FROM car WHERE licensePlate = :licensePlate")
    void deleteCar(String licensePlate);

    @Query("SELECT COUNT(*) FROM car")
    int getNumberOfCars();

}
