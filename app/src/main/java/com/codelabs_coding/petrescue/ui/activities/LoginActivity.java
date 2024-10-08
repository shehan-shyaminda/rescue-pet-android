package com.codelabs_coding.petrescue.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.models.UserModel;
import com.codelabs_coding.petrescue.utils.CommonUtils;
import com.codelabs_coding.petrescue.utils.SpUtils;
import com.codelabs_coding.petrescue.utils.networkUtils.RetrofitCallback;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    private EditText txtUserName, txtPassword;
    private LinearLayout txtSignUp;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        btnLogin.setOnClickListener(v -> getMyDetails());
        txtSignUp.setOnClickListener(v -> CommonUtils.startActivity(LoginActivity.this, RegisterActivity.class));
    }

    private void initViews() {
        txtUserName = findViewById(R.id.txt_username);
        txtPassword = findViewById(R.id.txt_password);
        btnLogin = findViewById(R.id.btn_login);
        txtSignUp = findViewById(R.id.txt_sign_up);
    }

    private void getMyDetails() {
        if (!validateInputs()) return;
        loadingDialog.showDialog(false);
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", CommonUtils.getStrEditView(txtUserName));
        map.put("userPassword", CommonUtils.getStrEditView(txtPassword));
        String json = new Gson().toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofitProvider.makeRequest(apiService.userLogin(requestBody), new RetrofitCallback<UserModel>() {
            @Override
            public void onSuccess(UserModel response) {
                loadingDialog.hideDialog();
                spUtils.saveString(SpUtils.KEY_USER_OBJECT, new Gson().toJson(response.getUser()));
                spUtils.saveString(SpUtils.KEY_USERID, response.getUser().getUserId());
                spUtils.saveString(SpUtils.KEY_USERNAME, response.getUser().getUsername());
                spUtils.saveBoolean(SpUtils.KEY_IS_LOGGED_IN, true);
                spUtils.saveString(SpUtils.KEY_AUTH_TOKEN, response.getAccessToken());

                HashMap<String, Object> map = new HashMap<>();
                map.put("fcmToken", spUtils.getString(SpUtils.FCM_TOKEN));
                String json = new Gson().toJson(map);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
                retrofitProvider.makeRequest(apiService.renewTokenFCM("Bearer " + spUtils.getString(SpUtils.KEY_AUTH_TOKEN), requestBody), new RetrofitCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i(TAG, "FCM Request onSuccess: " + response);
                        CommonUtils.startActivity(LoginActivity.this, MainActivity.class);
                        finish();
                    }

                    @Override
                    public void onError(int statusCode, String errorMessage) {
                        Log.e(TAG, "FCM Request failed with code: " + statusCode);
                        CommonUtils.startActivity(LoginActivity.this, MainActivity.class);
                        finish();
                    }
                });
            }

            @Override
            public void onError(int statusCode, String errorMessage) {
                loadingDialog.hideDialog();
                Log.e(TAG, "Request failed with code: " + statusCode);
                Toast.makeText(LoginActivity.this, "Please Check your Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean validateInputs() {
        return !CommonUtils.getStrEditView(txtUserName).isEmpty() && !CommonUtils.getStrEditView(txtPassword).isEmpty();
    }
}