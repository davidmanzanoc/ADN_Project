package com.example.adn_temp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adn_temp.R;
import com.example.domain.model.Vehicle;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {

    private List<Vehicle> vehicleList;

    public VehicleAdapter(List<Vehicle> vehicleList, Activity activity) {
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehicle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.ViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);
        holder.bindData(vehicle);
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView etLicensePlate;
        private TextView etEntryDate;
        private Button btCollect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etLicensePlate = itemView.findViewById(R.id.tvLicensePlate);
            etEntryDate = itemView.findViewById(R.id.tvEntryDate);
            btCollect = itemView.findViewById(R.id.btCollect);
        }

        private void bindData(Vehicle vehicle) {
            etLicensePlate.setText(vehicle.getLicensePlate());
            etEntryDate.setText(vehicle.getEntryDate().toString());
            btCollect.setOnClickListener(v -> {
                //collectParkingService
            });
        }
    }
}
