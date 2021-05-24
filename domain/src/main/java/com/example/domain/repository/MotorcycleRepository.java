package com.example.domain.repository;

import com.example.domain.model.Motorcycle;

import java.util.List;

public interface MotorcycleRepository {

    void saveMotorcycle(Motorcycle motorcycle);

    void deleteMotorcycle(Motorcycle motorcycle);

    int getNumberOfMotorcycles();

    List<Motorcycle> getMotorcycles();

}
