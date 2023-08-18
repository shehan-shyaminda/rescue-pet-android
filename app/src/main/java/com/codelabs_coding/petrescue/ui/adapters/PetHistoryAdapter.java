package com.codelabs_coding.petrescue.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.models.UserModel;
import com.codelabs_coding.petrescue.utils.CommonUtils;

import java.util.List;

public class PetHistoryAdapter extends RecyclerView.Adapter<PetHistoryAdapter.MyViewHolder> {

    private List<UserModel.User.Pet.LocationHistory> dataList;
    private String petName;


    public PetHistoryAdapter(List<UserModel.User.Pet.LocationHistory> dataList, String petName) {
        this.dataList = dataList;
        this.petName = petName;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.petName.setText(petName);
        holder.petLocation.setText("Last Location: " + dataList.get(position).getLatitude() + ", " + dataList.get(position).getLongitude());
        holder.timestamp.setText("Last Updated Time: " + CommonUtils.formatTimeStamp(dataList.get(position).getDateTime()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView petName;
        private final TextView petLocation;
        private final TextView timestamp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            petName = itemView.findViewById(R.id.txtPetName);
            petLocation = itemView.findViewById(R.id.txtLastLocation);
            timestamp = itemView.findViewById(R.id.txtLastTime);
        }
    }
}
