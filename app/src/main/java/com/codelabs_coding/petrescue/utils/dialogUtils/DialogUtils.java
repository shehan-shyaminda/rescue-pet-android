package com.codelabs_coding.petrescue.utils.dialogUtils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.codelabs_coding.petrescue.models.UserModel;
import com.codelabs_coding.petrescue.ui.adapters.PetsAdapter;
import com.codelabs_coding.petrescue.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;
import java.util.stream.Collectors;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

public class DialogUtils {

    public static void showSimpleBottomMenuDialog(Context context, List<String> items, OnItemSelectedListener listener) {
        View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.fragment_adapter_bottom_sheet, null);

        ListView listView = bottomSheetView.findViewById(R.id.listView);
        PetsAdapter adapter = new PetsAdapter(context, items);
        listView.setAdapter(adapter);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            listener.onItemSelected(position);
            bottomSheetDialog.dismiss();
        });
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

    public static void showCancelOkDialog(Activity activity, String title, String message, String okText, String cancelText, final OnDialogClickListener listener) {
        MaterialDialog mDialog = new MaterialDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(okText, (dialogInterface, which) -> {
                    if (listener != null) {
                        listener.onPositiveClick();
                    }
                    dialogInterface.dismiss();
                })
                .setNegativeButton(cancelText, (dialogInterface, which) -> {
                    if (listener != null) {
                        listener.onNegativeClick();
                    }
                    dialogInterface.dismiss();
                })
                .build();

        mDialog.show();
    }

    public interface OnDialogClickListener {
        void onPositiveClick();
        void onNegativeClick();
    }
}
