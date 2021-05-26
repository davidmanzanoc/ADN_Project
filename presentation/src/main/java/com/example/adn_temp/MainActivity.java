package com.example.adn_temp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.adn_temp.viewmodel.ParkingViewModel;
import com.example.domain.model.Car;
import com.example.domain.model.Motorcycle;
import com.example.domain.model.Vehicle;

import java.time.LocalDateTime;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private EditText etLicensePlate;
    private EditText etCylinderCapacity;
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
        etCylinderCapacity = findViewById(R.id.etCylinderCapacity);
        rbMotorcycle = findViewById(R.id.rbMotorcycle);
        rbCar = findViewById(R.id.rbCar);
        btSaveVehicle = findViewById(R.id.btSaveVehicle);
        rbMotorcycle.setOnClickListener(v -> etCylinderCapacity.setVisibility(View.VISIBLE));
        rbCar.setOnClickListener(v -> {
            etCylinderCapacity.setVisibility(View.GONE);
        });
        btSaveVehicle.setOnClickListener(v -> {
            saveVehicle();
        });
    }

    private void saveVehicle() {
        String cylinderCapacity = etCylinderCapacity.getText().toString();
        if (rbCar.isChecked()) {
            Vehicle vehicle = new Car(etLicensePlate.getText().toString(), LocalDateTime.now());
            parkingViewModel.saveVehicle(vehicle).observe(this, result ->
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show());
        } else if (rbMotorcycle.isChecked() && !cylinderCapacity.equals("")){
            int cylinderCapacityInt = Integer.parseInt(cylinderCapacity);
            Vehicle vehicle = new Motorcycle(etLicensePlate.getText().toString(), LocalDateTime.now(), cylinderCapacityInt);
            parkingViewModel.saveVehicle(vehicle).observe(this, result ->
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
