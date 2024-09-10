package com.codelabs_coding.petrescue.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.models.UserModel;
import com.codelabs_coding.petrescue.utils.CommonUtils;
import com.codelabs_coding.petrescue.utils.MsgEvent;
import com.codelabs_coding.petrescue.utils.RxBus;
import com.codelabs_coding.petrescue.utils.RxSubscriptions;
import com.codelabs_coding.petrescue.utils.SpUtils;
import com.codelabs_coding.petrescue.utils.networkUtils.RetrofitCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.util.HashMap;

import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RegisterActivity extends BaseActivity {

    private static final String TAG = "RegisterActivity";
    private LatLng selfLocation;
    private EditText txtUserName, txtPassword, txtConPassword;
    private LinearLayout txtSignIn;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtUserName = findViewById(R.id.txt_username);
        txtPassword = findViewById(R.id.txt_password);
        txtConPassword = findViewById(R.id.txt_confirm_password);
        btnRegister = findViewById(R.id.btn_register);
        txtSignIn = findViewById(R.id.txt_sign_in);

        checkForPermissions();

        btnRegister.setOnClickListener(v -> registerUser());
        txtSignIn.setOnClickListener(v -> CommonUtils.startActivity(RegisterActivity.this, LoginActivity.class));
    }

    private void registerUser() {
        if (!validateInputs()) return;
        if (!txtPassword.getText().toString().equals(txtConPassword.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Passwords Must be Same!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selfLocation == null) {
            Toast.makeText(RegisterActivity.this, "Something went wrong.\n Please try again later!", Toast.LENGTH_SHORT).show();
            return;
        }
        loadingDialog.showDialog(false);
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", CommonUtils.getStrEditView(txtUserName));
        map.put("userPassword", CommonUtils.getStrEditView(txtPassword));
        map.put("userLongitude", selfLocation.longitude);
        map.put("userLatitude", selfLocation.latitude);
        String json = new Gson().toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofitProvider.makeRequest(apiService.CreateUser(requestBody), new RetrofitCallback<UserModel>() {
            @Override
            public void onSuccess(UserModel response) {
                loadingDialog.hideDialog();
                CommonUtils.startActivity(RegisterActivity.this, MainActivity.class);
                finish();
            }

            @Override
            public void onError(int statusCode, String errorMessage) {
                loadingDialog.hideDialog();
                Log.e(TAG, "Request failed with code: " + statusCode);
                Toast.makeText(RegisterActivity.this, "Something went wrong.\n Please try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean validateInputs() {
        return !CommonUtils.getStrEditView(txtUserName).isEmpty() && !CommonUtils.getStrEditView(txtPassword).isEmpty()
                && !CommonUtils.getStrEditView(txtConPassword).isEmpty();
    }

    private void checkForPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            getLastLocation();
        }
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            selfLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        }
                    });
        }
    }
}