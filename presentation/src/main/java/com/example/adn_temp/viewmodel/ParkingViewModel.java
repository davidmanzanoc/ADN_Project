package com.example.adn_temp.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.model.Car;
import com.example.domain.model.Motorcycle;
import com.example.domain.model.Vehicle;
import com.example.domain.service.ParkingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ParkingViewModel extends ViewModel {

    private final ParkingService parkingService;
    private MutableLiveData<List<Vehicle>> vehicleList;
    private MutableLiveData<String> vehicleSaved;
    private MutableLiveData<Integer> parkingBill;

    @ViewModelInject
    public ParkingViewModel(ParkingService parkingService) {
        this.parkingService = parkingService;
        this.vehicleList = new MutableLiveData<>();
        this.vehicleSaved  = new MutableLiveData<>();
        this.parkingBill = new MutableLiveData<>();
        vehicleList = parkingService.getVehicles();
    }

    public LiveData<String> saveVehicle(Vehicle vehicle) {
        int entryDate = vehicle.getEntryDate().getDayOfWeek().getValue();
        try {
            if (vehicle instanceof Car) {
                Car car = (Car) vehicle;
                parkingService.saveCar(car, entryDate);
            } else {
                Motorcycle motorcycle = (Motorcycle) vehicle;
                parkingService.saveMotorcycle(motorcycle, entryDate);
            }
            vehicleList.getValue().add(vehicle);
            vehicleSaved.setValue("Vehiculo guardado con éxito!");
        } catch (Exception e) {
            vehicleSaved.setValue(e.getMessage());
        }
        return vehicleSaved;
    }

    public MutableLiveData<List<Vehicle>> getVehicleMutableList() {
        return vehicleList;
    }

    public LiveData<Integer> collectParkingService(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            parkingBill.setValue(parkingService.carParkingCost(car, LocalDateTime.now()));
            parkingService.deleteCar(car);
        } else {
            Motorcycle motorcycle = (Motorcycle) vehicle;
            parkingBill.setValue(parkingService.motorcycleParkingCost(motorcycle, LocalDateTime.now()));
            parkingService.deleteMotorcycle(motorcycle);
        }
        Objects.requireNonNull(vehicleList.getValue()).remove(vehicle);
        return parkingBill;
    }
}
