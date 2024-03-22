package com.codelabs_coding.petrescue.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codelabs_coding.petrescue.utils.BaseInterface;
import com.codelabs_coding.petrescue.utils.MsgEvent;
import com.codelabs_coding.petrescue.utils.SpUtils;
import com.codelabs_coding.petrescue.utils.dialogUtils.LoadingDialog;
import com.codelabs_coding.petrescue.utils.networkUtils.ApiService;
import com.codelabs_coding.petrescue.utils.networkUtils.RetrofitProvider;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class BaseActivity extends AppCompatActivity implements BaseInterface {

    static final int DOUBLE_PRESS_EXIT_TIME = 2000;
    static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    RetrofitProvider retrofitProvider;
    LoadingDialog loadingDialog;
    ApiService apiService;
    SpUtils spUtils;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        retrofitProvider = new RetrofitProvider();
        apiService = retrofitProvider.getApiService();
        loadingDialog = new LoadingDialog(this);
        spUtils = new SpUtils(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        registerRxBus();
    }

    @Override
    public void registerRxBus() {

    }

    @Override
    public void removeRxBus() {

    }

    public void showDialog(Boolean isCancelable) {
        if (!loadingDialog.isShowing()) {
            loadingDialog.setCancelable(isCancelable);
            loadingDialog.show();
        }
    }

    public void hideDialog() {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
