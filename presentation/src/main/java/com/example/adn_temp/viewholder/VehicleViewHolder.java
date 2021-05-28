package com.example.adn_temp.viewholder;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adn_temp.ParkingServiceActivity;
import com.example.adn_temp.R;
import com.example.domain.model.Vehicle;

public class VehicleViewHolder extends RecyclerView.ViewHolder{

    private final TextView etLicensePlate;
    private final TextView etEntryDate;
    private final Button btCollect;

    public VehicleViewHolder(@NonNull View itemView) {
        super(itemView);
        etLicensePlate = itemView.findViewById(R.id.tvLicensePlate);
        etEntryDate = itemView.findViewById(R.id.tvEntryDate);
        btCollect = itemView.findViewById(R.id.btCollect);
    }

    public void bindData(Vehicle vehicle, Activity activity) {
        etLicensePlate.setText(vehicle.getLicensePlate());
        etEntryDate.setText(vehicle.getEntryDate().toString());
        btCollect.setOnClickListener(v -> {
            ParkingServiceActivity parkingServiceActivity = (ParkingServiceActivity) activity;
            parkingServiceActivity.collectParkingService(vehicle);
        });
    }
}
