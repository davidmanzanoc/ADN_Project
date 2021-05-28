package com.example.domain.vehicle.motorcycle.repository;

import com.example.domain.vehicle.motorcycle.model.Motorcycle;

import java.util.List;

public interface MotorcycleRepository {

    void saveMotorcycle(Motorcycle motorcycle);

    void deleteMotorcycle(Motorcycle motorcycle);

    int getNumberOfMotorcycles();

    List<Motorcycle> getMotorcycles();

}
