package com.example.adn_temp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.adn_temp.adapter.VehicleAdapter;
import com.example.adn_temp.viewmodel.ParkingViewModel;
import com.example.domain.model.Car;
import com.example.domain.model.Motorcycle;
import com.example.domain.model.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ParkingServiceActivity extends AppCompatActivity {

    private EditText editTextLicensePlate;
    private EditText editTextCylinderCapacity;
    private RadioButton radioButtonMotorcycle;
    private RadioButton radioButtonCar;
    private Button buttonSaveVehicle;
    private RecyclerView recyclerViewVehicles;
    private VehicleAdapter vehicleAdapter;

    private ParkingViewModel parkingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_service);
        initElements();
        onClickManager();
    }

    private void initElements() {
        parkingViewModel = new ViewModelProvider(this).get(ParkingViewModel.class);
        recyclerViewVehicles = findViewById(R.id.recyclerViewVehicles);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewVehicles.setLayoutManager(linearLayoutManager);
        editTextLicensePlate = findViewById(R.id.editTextLicensePlate);
        editTextCylinderCapacity = findViewById(R.id.editTextCylinderCapacity);
        radioButtonMotorcycle = findViewById(R.id.radioButtonMotorcycle);
        radioButtonCar = findViewById(R.id.radioButtonCar);
        buttonSaveVehicle = findViewById(R.id.buttonSaveVehicle);
        parkingViewModel.getVehicleMutableList().observe(this, this::updateAdapter);
    }

    private void updateAdapter(List<Vehicle> vehicleList) {
        vehicleAdapter = new VehicleAdapter(vehicleList, this);
        recyclerViewVehicles.setAdapter(vehicleAdapter);
    }

    private void onClickManager() {
        radioButtonMotorcycle.setOnClickListener(v -> editTextCylinderCapacity.setVisibility(View.VISIBLE));
        radioButtonCar.setOnClickListener(v -> editTextCylinderCapacity.setVisibility(View.GONE));
        buttonSaveVehicle.setOnClickListener(v -> saveVehicle());
    }

    private void saveVehicle() {
        String cylinderCapacity = editTextCylinderCapacity.getText().toString();
        String licensePlate = editTextLicensePlate.getText().toString();
        if (radioButtonCar.isChecked() && !licensePlate.equals("")) {
            Vehicle vehicle = new Car(licensePlate, LocalDateTime.now());
            parkingViewModel.saveVehicle(vehicle).observe(this, result ->
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show());
            clearFields();
        } else if (radioButtonMotorcycle.isChecked() && !licensePlate.equals("") && !cylinderCapacity.equals("")) {
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
        editTextCylinderCapacity.setText("");
        editTextLicensePlate.setText("");
    }

    public void collectParkingService(Vehicle vehicle) {
        parkingViewModel.collectParkingService(vehicle).observe(this, billParkingService -> {
            Toast.makeText(this, "Total a pagar: " + billParkingService, Toast.LENGTH_LONG).show();
            vehicleAdapter.notifyDataSetChanged();
        });
    }
}
