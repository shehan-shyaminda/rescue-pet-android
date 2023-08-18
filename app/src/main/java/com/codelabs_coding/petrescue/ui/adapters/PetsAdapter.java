package com.codelabs_coding.petrescue.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codelabs_coding.petrescue.R;

import java.util.List;

public class PetsAdapter extends ArrayAdapter<String> {

    public PetsAdapter(Context context, List<String> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pet, parent, false);
        }

        String currency = getItem(position);
        TextView textView = convertView.findViewById(R.id.tv_petName);
        textView.setText(currency);

        return convertView;
    }
}