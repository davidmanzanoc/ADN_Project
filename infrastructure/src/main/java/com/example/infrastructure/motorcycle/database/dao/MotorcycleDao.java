package com.example.infrastructure.motorcycle.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infrastructure.motorcycle.database.entity.MotorcycleEntity;

import java.util.List;

@Dao
public interface MotorcycleDao {

    @Insert
    void saveMotorcycle(MotorcycleEntity motorcycleEntity);

    @Query("SELECT * FROM motorcycle")
    List<MotorcycleEntity> getMotorcycles();

    @Query("DELETE FROM motorcycle WHERE licensePlate = :licensePlate")
    void deleteMotorcycle(String licensePlate);

    @Query("SELECT COUNT(*) FROM motorcycle")
    int getNumberOfMotorcycles();

}
