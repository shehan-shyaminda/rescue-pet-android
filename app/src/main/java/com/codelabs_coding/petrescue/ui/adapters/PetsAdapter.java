package com.codelabs_coding.petrescue.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.utils.dialogUtils.DialogUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;
import java.util.Map;

public class PetsAdapter extends RecyclerView.Adapter<ViewHolder> {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pet, parent, false);
        return new ViewHolder(view, listener, bottomSheetDialog);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, String> bean = mList.get(position);
        holder.tvName.setText(bean.getOrDefault("petName", "Bruno"));
        holder.tvContent.setText(bean.getOrDefault("petType", "Dog"));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

