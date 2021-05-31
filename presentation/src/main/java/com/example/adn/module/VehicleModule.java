package com.example.adn.module;

import com.example.domain.vehicle.car.repository.CarRepository;
import com.example.domain.vehicle.motorcycle.repository.MotorcycleRepository;
import com.example.infrastructure.repository.CarRepositoryRoom;
import com.example.infrastructure.repository.MotorcycleRepositoryRoom;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn({ActivityComponent.class})
public interface VehicleModule {

    @Binds
    CarRepository injectCarRepository(CarRepositoryRoom carRepositoryRoom);

    @Binds
    MotorcycleRepository injectMotorcycleRepository(MotorcycleRepositoryRoom motorcycleRepositoryRoom);

}
