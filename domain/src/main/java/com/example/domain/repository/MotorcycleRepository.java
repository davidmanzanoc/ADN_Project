package com.example.domain.repository;

import com.example.domain.model.Motorcycle;

public interface MotorcycleRepository {

    void saveMotorcycle(Motorcycle motorcycle);

    void deleteMotorcycle(Motorcycle motorcycle);

    int getNumberOfMotorcycles();

}
