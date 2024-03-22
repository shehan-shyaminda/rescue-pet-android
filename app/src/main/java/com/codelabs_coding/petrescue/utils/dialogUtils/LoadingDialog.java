package com.codelabs_coding.petrescue.utils.dialogUtils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.DoubleBounce;

public class LoadingDialog extends Dialog {

    private final SpinKitView spinKitView;

    public LoadingDialog(Context context) {
        super(context);

        FrameLayout frameLayout = new FrameLayout(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER;
        frameLayout.setLayoutParams(layoutParams);
        spinKitView = new SpinKitView(context);
        spinKitView.setIndeterminateDrawable(new DoubleBounce());
        FrameLayout.LayoutParams spinKitLayoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        spinKitLayoutParams.gravity = Gravity.CENTER;
        spinKitView.setLayoutParams(spinKitLayoutParams);
        frameLayout.addView(spinKitView);
        setContentView(frameLayout);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }


    public void showDialog(Boolean isCancelable) {
        if (!isShowing()) {
            setCancelable(isCancelable);
            show();
        }
    }

    public void hideDialog() {
        if (isShowing()) {
            dismiss();
        }
    }
}
