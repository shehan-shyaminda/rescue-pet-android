package com.codelabs_coding.petrescue.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.models.UserModel;
import com.codelabs_coding.petrescue.utils.CommonUtils;
import com.codelabs_coding.petrescue.utils.SpUtils;
import com.codelabs_coding.petrescue.utils.dialogUtils.DialogUtils;
import com.codelabs_coding.petrescue.utils.dialogUtils.LoadingDialog;
import com.codelabs_coding.petrescue.utils.networkUtils.ApiService;
import com.codelabs_coding.petrescue.utils.networkUtils.RetrofitCallback;
import com.codelabs_coding.petrescue.utils.networkUtils.RetrofitProvider;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddPetActivity extends AppCompatActivity {

    private static final String TAG = "AddPetActivity";
    private final List<String> petTypeList = new ArrayList<>(Arrays.asList(
            "- Select Pet Type -",
            "Dog",
            "Cat"
    ));
    private RetrofitProvider retrofitProvider;
    private LoadingDialog loadingDialog;
    private ApiService apiService;
    private SpUtils spUtils;
    private TextView txtPetTypes;
    private Button btnAddPet;
    private EditText etPetName, etPetBread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        txtPetTypes = findViewById(R.id.txtPetType);
        etPetName = findViewById(R.id.etPetName);
        etPetBread = findViewById(R.id.etPetBread);
        btnAddPet = findViewById(R.id.btnAddPet);

        retrofitProvider = new RetrofitProvider();
        apiService = retrofitProvider.getApiService();
        loadingDialog = new LoadingDialog(this);
        spUtils = new SpUtils(this);

        txtPetTypes.setOnClickListener(v -> showCustomBottomSheet());
        btnAddPet.setOnClickListener(v -> addNewPet());
    }

    private void showCustomBottomSheet() {
        DialogUtils.showSimpleBottomMenuDialog(this, petTypeList, position -> {
            txtPetTypes.setText(petTypeList.get(position));
        });
    }

    private void addNewPet() {
        if (!validateInputs()) return;
        if (txtPetTypes.getText().equals(petTypeList.get(0))) {
            Toast.makeText(AddPetActivity.this, "Please select pet type!", Toast.LENGTH_SHORT).show();
            return;
        }
        loadingDialog.showDialog(false);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", spUtils.getString(SpUtils.KEY_USERID));
        map.put("petNickname", CommonUtils.getStrEditView(etPetName));
        map.put("petType", txtPetTypes.getText().toString());
        map.put("petBread", CommonUtils.getStrEditView(etPetBread));
        String json = new Gson().toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofitProvider.makeRequest(apiService.AddNewPet("Bearer " + spUtils.getString(SpUtils.KEY_AUTH_TOKEN) ,requestBody), new RetrofitCallback<UserModel.User.Pet>() {
            @Override
            public void onSuccess(UserModel.User.Pet response) {
                loadingDialog.hideDialog();
                finish();
            }

            @Override
            public void onError(int statusCode, String errorMessage) {
                loadingDialog.hideDialog();
                Log.e(TAG, "Request failed with code: " + statusCode);
                Toast.makeText(AddPetActivity.this, "Something went wrong.\n Please try again later!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable throwable) {
                loadingDialog.dismiss();
                Log.e(TAG, "onError: " + throwable.getLocalizedMessage());
                throwable.printStackTrace();
            }
        });
    }

    private Boolean validateInputs(){
        return !CommonUtils.getStrEditView(etPetName).isEmpty() && !CommonUtils.getStrEditView(etPetBread).isEmpty();
    }
}