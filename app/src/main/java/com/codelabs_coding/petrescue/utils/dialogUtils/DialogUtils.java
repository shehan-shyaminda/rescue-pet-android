package com.codelabs_coding.petrescue.utils.dialogUtils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.ui.adapters.PetsAdapter;
import com.codelabs_coding.petrescue.ui.adapters.TypesAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;
import java.util.Map;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;

public class DialogUtils {


    public static void showBottomMenuDialog(Context context, List<String> items, OnItemSelectedListener listener) {
        View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.fragment_adapter_bottom_sheet, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(bottomSheetView);
        RecyclerView mRecyclerview = bottomSheetView.findViewById(R.id.lvList);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(context));
        TypesAdapter mAdapter = new TypesAdapter(context, items, listener, bottomSheetDialog);
        mRecyclerview.setAdapter(mAdapter);
        bottomSheetDialog.show();
    }

    public static void showSimpleBottomMenuDialog(Context context, List<Map<String, String>> items, OnItemSelectedListener listener) {
        View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.fragment_adapter_bottom_sheet, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(bottomSheetView);
        RecyclerView mRecyclerview = bottomSheetView.findViewById(R.id.lvList);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(context));
        PetsAdapter mAdapter = new PetsAdapter(context, items, listener, bottomSheetDialog);
        mRecyclerview.setAdapter(mAdapter);
        bottomSheetDialog.show();
    }

    public static void showCancelOkDialog(Activity activity, String title, String message, String okText, String cancelText, final OnDialogClickListener listener) {
        MaterialDialog mDialog = new MaterialDialog.Builder(activity).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(okText, (dialogInterface, which) -> {
            if (listener != null) {
                listener.onPositiveClick();
            }
            dialogInterface.dismiss();
        }).setNegativeButton(cancelText, (dialogInterface, which) -> {
            if (listener != null) {
                listener.onNegativeClick();
            }
            dialogInterface.dismiss();
        }).build();

        mDialog.show();
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

    public interface OnDialogClickListener {
        void onPositiveClick();

        void onNegativeClick();
    }
}
