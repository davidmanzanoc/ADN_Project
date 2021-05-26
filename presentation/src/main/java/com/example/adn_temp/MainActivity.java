package com.example.adn_temp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.adn_temp.viewmodel.ParkingViewModel;
import com.example.domain.model.Car;
import com.example.domain.model.Vehicle;

import java.time.LocalDateTime;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private EditText etLicensePlate;
    private RadioButton rbMotorcycle;
    private RadioButton rbCar;
    private Button btSaveVehicle;

    private ParkingViewModel parkingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
    }

    private void initElements() {
        parkingViewModel = new ViewModelProvider(this).get(ParkingViewModel.class);
        etLicensePlate = findViewById(R.id.etLicensePlate);
        rbMotorcycle = findViewById(R.id.rbMotorcycle);
        rbCar = findViewById(R.id.rbCar);
        btSaveVehicle = findViewById(R.id.btSaveVehicle);
        btSaveVehicle.setOnClickListener(v -> {
            Vehicle vehicle = new Car(etLicensePlate.getText().toString(), LocalDateTime.now());
            parkingViewModel.saveVehicle(vehicle);
        });
    }
}