package com.example.adn.viewmodel;

import android.content.Context;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.adn.R;
import com.example.domain.parking.exception.GlobalException;
import com.example.domain.vehicle.vehicle.model.Vehicle;
import com.example.domain.parking.service.ParkingService;

import java.util.List;
import java.util.Objects;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class ParkingViewModel extends ViewModel {

    private final ParkingService parkingService;
    private MutableLiveData<List<Vehicle>> vehicleList;
    private MutableLiveData<String> vehicleSaved;
    private MutableLiveData<Integer> parkingBill;
    private Context context;

    @ViewModelInject
    public ParkingViewModel(ParkingService parkingService, @ApplicationContext Context context) {
        this.parkingService = parkingService;
        this.vehicleList = new MutableLiveData<>();
        this.vehicleSaved = new MutableLiveData<>();
        this.parkingBill = new MutableLiveData<>();
        this.context = context;
        vehicleList = parkingService.getVehicles();
    }

    public LiveData<String> saveVehicle(Vehicle vehicle) {
        try {
            vehicle.saveVehicle(parkingService);
            vehicleList.getValue().add(vehicle);
            vehicleSaved.setValue(context.getString(R.string.vehicleSaved));
        } catch (Exception exception) {
            throw new GlobalException(context.getString(R.string.vehicleNotSavedException), exception);
        }
        return vehicleSaved;
    }

    public MutableLiveData<List<Vehicle>> getVehicleMutableList() {
        return vehicleList;
    }

    public LiveData<Integer> collectParkingService(Vehicle vehicle) {
        parkingBill.setValue(vehicle.parkingCost(parkingService));
        Objects.requireNonNull(vehicleList.getValue()).remove(vehicle);
        return parkingBill;
    }
}
