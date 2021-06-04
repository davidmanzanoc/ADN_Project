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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class ParkingViewModel extends ViewModel {

    private final ParkingService parkingService;
    private final ExecutorService executorService;
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
        executorService = Executors.newFixedThreadPool(4);
        getVehicleMutableList();
    }

    public LiveData<String> saveVehicle(Vehicle vehicle) {
        try {
            executorService.execute(() -> vehicle.saveVehicle(parkingService));
            Objects.requireNonNull(vehicleList.getValue()).add(vehicle);
            vehicleSaved.setValue(context.getString(R.string.vehicleSaved));
        } catch (Exception exception) {
            throw new GlobalException(context.getString(R.string.vehicleNotSavedException), exception);
        }
        return vehicleSaved;
    }

    public MutableLiveData<List<Vehicle>> getVehicleMutableList() {
        executorService.execute(() -> vehicleList.postValue(parkingService.getVehicles()));
        return vehicleList;
    }

    public LiveData<Integer> collectParkingService(Vehicle vehicle) {
        executorService.execute(() -> parkingBill.postValue(vehicle.parkingCost(parkingService)));
        Objects.requireNonNull(vehicleList.getValue()).remove(vehicle);
        return parkingBill;
    }
}
