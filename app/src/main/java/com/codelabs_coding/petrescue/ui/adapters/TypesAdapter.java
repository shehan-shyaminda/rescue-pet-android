package com.codelabs_coding.petrescue.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.utils.dialogUtils.DialogUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class TypesAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<String> mList;
    private final DialogUtils.OnItemSelectedListener listener;
    private final BottomSheetDialog bottomSheetDialog;

    public TypesAdapter(Context context, List<String> mList, DialogUtils.OnItemSelectedListener listener, BottomSheetDialog bottomSheetDialog) {
        this.mContext = context;
        this.mList = mList;
        this.listener = listener;
        this.bottomSheetDialog = bottomSheetDialog;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_type, parent, false), listener, bottomSheetDialog);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvContent.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;

        public ViewHolder(@NonNull View itemView, DialogUtils.OnItemSelectedListener listener, BottomSheetDialog bottomSheetDialog) {
            super(itemView);
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