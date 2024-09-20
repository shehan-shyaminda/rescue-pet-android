package com.codelabs_coding.petrescue.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.models.UserModel;
import com.codelabs_coding.petrescue.utils.CommonUtils;
import com.codelabs_coding.petrescue.utils.MsgEvent;
import com.codelabs_coding.petrescue.utils.RxBus;
import com.codelabs_coding.petrescue.utils.SpUtils;
import com.codelabs_coding.petrescue.utils.dialogUtils.DialogUtils;
import com.codelabs_coding.petrescue.utils.networkUtils.RetrofitCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddPetActivity extends BaseActivity {

    private static final String TAG = "AddPetActivity";
    private final List<String> petTypeList = new ArrayList<>(Arrays.asList(
            "- Select Pet Type -",
            "Dog",
            "Cat"
    ));
    private int petPosition = 0;
    private TextView txtPetTypes;
    private Button btnAddPet;
    private EditText etPetName, etPetBread;
    private LatLng selfLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        txtPetTypes = findViewById(R.id.txtPetType);
        etPetName = findViewById(R.id.etPetName);
        etPetBread = findViewById(R.id.etPetBread);
        btnAddPet = findViewById(R.id.btnAddPet);

        txtPetTypes.setOnClickListener(v -> showCustomBottomSheet());
        btnAddPet.setOnClickListener(v -> addNewPet());

        checkForPermissions();
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

    private void showCustomBottomSheet() {
        DialogUtils.showBottomMenuDialog(this, petTypeList, position -> {
            txtPetTypes.setText(petTypeList.get(position));
            petPosition = position;
        });
    }

    private void addNewPet() {
        if (!validateInputs()) return;
        if (petPosition == 0) {
            Toast.makeText(AddPetActivity.this, "Please select pet type!", Toast.LENGTH_SHORT).show();
            return;
        }
        showDialog(false);
        HashMap<String, Object> locationMap = new HashMap<>();
        locationMap.put("petLongitude", selfLocation.longitude);
        locationMap.put("petLatitude", selfLocation.latitude);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", spUtils.getString(SpUtils.KEY_USERID));
        map.put("petNickname", CommonUtils.getStrEditView(etPetName));
        map.put("petType", String.valueOf(petPosition));
        map.put("petBread", CommonUtils.getStrEditView(etPetBread));
        map.put("petLocationHistory",locationMap);
        String json = new Gson().toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofitProvider.makeRequest(apiService.addNewPet("Bearer " + spUtils.getString(SpUtils.KEY_AUTH_TOKEN), requestBody), new RetrofitCallback<UserModel.User.Pet>() {
            @Override
            public void onSuccess(UserModel.User.Pet response) {
                hideDialog();
                RxBus.getDefault().post(new MsgEvent(MsgEvent.UPDATE_PET_LIST));
                finish();
            }

            @Override
            public void onError(int statusCode, String errorMessage) {
                hideDialog();
                Log.e(TAG, "Request failed with code: " + statusCode);
                Toast.makeText(AddPetActivity.this, "Something went wrong.\n Please try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean validateInputs() {
        return !CommonUtils.getStrEditView(etPetName).isEmpty() && !CommonUtils.getStrEditView(etPetBread).isEmpty();
    }
}