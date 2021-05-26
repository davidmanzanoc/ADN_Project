package com.example.adn_temp.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import com.example.domain.model.Car;
import com.example.domain.model.Motorcycle;
import com.example.domain.model.Vehicle;
import com.example.domain.service.ParkingService;

public class ParkingViewModel extends ViewModel {

    private final ParkingService parkingService;

    @ViewModelInject
    public ParkingViewModel(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public void saveVehicle(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            parkingService.saveCar(car, car.getEntryDate().getDayOfWeek().getValue());
        } else {
            Motorcycle motorcycle = (Motorcycle) vehicle;
            parkingService.saveMotorcycle(motorcycle, motorcycle.getEntryDate().getDayOfWeek().getValue());
        }
    }
}
