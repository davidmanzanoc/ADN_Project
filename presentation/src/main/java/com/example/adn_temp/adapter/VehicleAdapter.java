package com.example.adn_temp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adn_temp.R;
import com.example.adn_temp.viewholder.VehicleViewHolder;
import com.example.domain.model.Vehicle;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleViewHolder> {

    private final List<Vehicle> vehicleList;
    private final Activity activity;

    public VehicleAdapter(List<Vehicle> vehicleList, Activity activity) {
        this.vehicleList = vehicleList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VehicleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehicle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);
        holder.bindData(vehicle, activity);
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }
}
