package com.codelabs_coding.petrescue.ui.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.models.PetsModal;
import com.codelabs_coding.petrescue.utils.CommonUtils;

import java.util.List;

public class PetHistoryAdapter extends RecyclerView.Adapter<PetHistoryAdapter.MyViewHolder> {

    private final List<PetsModal.PetLocation> dataList;
    private final String petName;
    private final Context context;

    public PetHistoryAdapter(List<PetsModal.PetLocation> dataList, String petName, Context context) {
        this.dataList = dataList;
        this.petName = petName;
        this.context = context;
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
        holder.petLocation.setText(String.format("Last Location: %s, %s", dataList.get(position).getPetLatitude(), dataList.get(position).getPetLongitude()));
        holder.timestamp.setText(String.format("Last Updated Time: %s", CommonUtils.formatTimeStamp(dataList.get(position).getTimeStamp())));
        holder.icArrow.setOnClickListener(view -> {
            String uri = String.format("http://maps.google.com/maps?q=loc:%f,%f",dataList.get(position).getPetLatitude(), dataList.get(position).getPetLongitude());
            CommonUtils.openGoogleMaps(Uri.parse(uri), context);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView petName;
        private final TextView petLocation;
        private final TextView timestamp;
        private final ImageView icArrow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            petName = itemView.findViewById(R.id.txtPetName);
            petLocation = itemView.findViewById(R.id.txtLastLocation);
            timestamp = itemView.findViewById(R.id.txtLastTime);
            icArrow = itemView.findViewById(R.id.icArrow);
        }
    }
}
