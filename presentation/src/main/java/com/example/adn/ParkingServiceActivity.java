package com.example.adn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adn.adapter.VehicleAdapter;
import com.example.adn.databinding.ActivityParkingServiceBinding;
import com.example.adn.viewmodel.ParkingViewModel;
import com.example.domain.vehicle.car.model.Car;
import com.example.domain.vehicle.motorcycle.model.Motorcycle;
import com.example.domain.vehicle.vehicle.model.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ParkingServiceActivity extends AppCompatActivity {

    private VehicleAdapter vehicleAdapter;

    private ParkingViewModel parkingViewModel;
    private ActivityParkingServiceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParkingServiceBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initElements();
        onClickManager();
    }

    private void initElements() {
        parkingViewModel = new ViewModelProvider(this).get(ParkingViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerViewVehicles.setLayoutManager(linearLayoutManager);
        parkingViewModel.getVehicleMutableList().observe(this, this::updateAdapter);
    }

    private void updateAdapter(List<Vehicle> vehicleList) {
        vehicleAdapter = new VehicleAdapter(vehicleList, this);
        binding.recyclerViewVehicles.setAdapter(vehicleAdapter);
    }

    private void onClickManager() {
        binding.radioButtonMotorcycle.setOnClickListener(v -> binding.editTextCylinderCapacity.setVisibility(View.VISIBLE));
        binding.radioButtonCar.setOnClickListener(v -> binding.editTextCylinderCapacity.setVisibility(View.GONE));
        binding.buttonSaveVehicle.setOnClickListener(v -> saveVehicle());
    }

    private void saveVehicle() {
        String cylinderCapacity = binding.editTextCylinderCapacity.getText().toString();
        String licensePlate = binding.editTextLicensePlate.getText().toString();
        if (binding.radioButtonCar.isChecked() && !licensePlate.equals("")) {
            Vehicle vehicle = new Car(licensePlate, LocalDateTime.now());
            parkingViewModel.saveVehicle(vehicle).observe(this, result ->
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show());
            clearFields();
        } else if (binding.radioButtonMotorcycle.isChecked() && !licensePlate.equals("") && !cylinderCapacity.equals("")) {
            int cylinderCapacityInt = Integer.parseInt(cylinderCapacity);
            Vehicle vehicle = new Motorcycle(licensePlate, LocalDateTime.now(), cylinderCapacityInt);
            parkingViewModel.saveVehicle(vehicle).observe(this, result ->
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show());
            clearFields();
        } else {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        }
        vehicleAdapter.notifyDataSetChanged();
    }

    private void clearFields() {
        binding.editTextCylinderCapacity.setText("");
        binding.editTextLicensePlate.setText("");
    }

    public void collectParkingService(Vehicle vehicle) {
        parkingViewModel.collectParkingService(vehicle).observe(this, billParkingService -> {
            Toast.makeText(this, "Total a pagar: " + billParkingService, Toast.LENGTH_LONG).show();
            vehicleAdapter.notifyDataSetChanged();
        });
    }
}
