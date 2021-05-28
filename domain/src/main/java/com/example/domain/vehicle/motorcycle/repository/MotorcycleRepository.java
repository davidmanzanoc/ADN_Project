package com.example.domain.vehicle.motorcycle.repository;

import com.example.domain.vehicle.motorcycle.model.Motorcycle;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MotorcycleRepository {

    void saveMotorcycle(Motorcycle motorcycle);

    void deleteMotorcycle(Motorcycle motorcycle);

    int getNumberOfMotorcycles();

    List<Motorcycle> getMotorcycles();

}
