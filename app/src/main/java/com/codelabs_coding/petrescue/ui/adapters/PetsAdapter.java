package com.codelabs_coding.petrescue.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.utils.dialogUtils.DialogUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PetsAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<Map<String, String>> mList;
    private final DialogUtils.OnItemSelectedListener listener;
    private final BottomSheetDialog bottomSheetDialog;

    public PetsAdapter(Context context, List<Map<String, String>> mList, DialogUtils.OnItemSelectedListener listener, BottomSheetDialog bottomSheetDialog) {
        this.mContext = context;
        this.mList = mList;
        this.listener = listener;
        this.bottomSheetDialog = bottomSheetDialog;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_pet, parent, false), listener, bottomSheetDialog);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Map<String, String> bean = mList.get(position);
        viewHolder.tvName.setText(bean.getOrDefault("petName", "Bruno"));
        viewHolder.tvContent.setText(bean.getOrDefault("petType", "Dog"));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvContent;

        public ViewHolder(@NonNull View itemView, DialogUtils.OnItemSelectedListener listener, BottomSheetDialog bottomSheetDialog) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_petName);
            tvContent = itemView.findViewById(R.id.tv_petType);
            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemSelected(position);
                    bottomSheetDialog.dismiss();
                }
            });
        }
    }
}