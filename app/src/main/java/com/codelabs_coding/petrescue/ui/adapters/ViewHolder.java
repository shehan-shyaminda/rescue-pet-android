package com.codelabs_coding.petrescue.ui.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.utils.dialogUtils.DialogUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ViewHolder extends RecyclerView.ViewHolder {
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
