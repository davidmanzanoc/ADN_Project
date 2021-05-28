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

    private final TextView textViewLicensePlate;
    private final TextView textViewEntryDate;
    private final Button buttonCollect;

    public VehicleViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewLicensePlate = itemView.findViewById(R.id.textViewLicensePlate);
        textViewEntryDate = itemView.findViewById(R.id.textViewEntryDate);
        buttonCollect = itemView.findViewById(R.id.buttonCollect);
    }

    public void bindData(Vehicle vehicle, Activity activity) {
        textViewLicensePlate.setText(vehicle.getLicensePlate());
        textViewEntryDate.setText(vehicle.getEntryDate().toString());
        buttonCollect.setOnClickListener(v -> {
            ParkingServiceActivity parkingServiceActivity = (ParkingServiceActivity) activity;
            parkingServiceActivity.collectParkingService(vehicle);
        });
    }
}
