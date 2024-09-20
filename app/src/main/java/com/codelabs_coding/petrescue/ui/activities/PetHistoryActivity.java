package com.codelabs_coding.petrescue.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.models.PetsModal;
import com.codelabs_coding.petrescue.models.UserModel;
import com.codelabs_coding.petrescue.ui.adapters.PetHistoryAdapter;
import com.codelabs_coding.petrescue.utils.SpUtils;
import com.codelabs_coding.petrescue.utils.networkUtils.RetrofitCallback;
import com.codelabs_coding.petrescue.utils.networkUtils.RetrofitProvider;
import com.google.gson.Gson;
//import com.codelabs_coding.petrescue.ui.adapters.PetHistoryAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PetHistoryActivity extends BaseActivity {

    private static final String TAG = "PetHistoryActivity";
    private RecyclerView rvList;
    private PetHistoryAdapter petHistoryAdapter;
    private String petId;
    private String petName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_history);
        rvList = findViewById(R.id.rv_list);
        petId = this.getIntent().getStringExtra("petId");
        petName = this.getIntent().getStringExtra("petName");
        getPetLocations(petId);
    }

    private void initAdapter(PetsModal response) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);
        petHistoryAdapter = new PetHistoryAdapter(response.getPetLocationHistory(), petName, this);
        rvList.setAdapter(petHistoryAdapter);
        petHistoryAdapter.notifyDataSetChanged();
    }

    private void getPetLocations(String petId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("petId", petId);
        String json = new Gson().toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofitProvider.makeRequest(apiService.getPet("Bearer " + spUtils.getString(SpUtils.KEY_AUTH_TOKEN), requestBody), new RetrofitCallback<PetsModal>() {
            @Override
            public void onSuccess(PetsModal response) {
                loadingDialog.hideDialog();
                if (null != response.getPetLocationHistory() && !response.getPetLocationHistory().isEmpty()) {
                    initAdapter(response);
                } else {
                    Toast.makeText(PetHistoryActivity.this, "No History founded for the Pet!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onError(int statusCode, String errorMessage) {
                loadingDialog.hideDialog();
                Log.e(TAG, "Request failed with code: " + statusCode);
                Toast.makeText(PetHistoryActivity.this, "Something went wrong.\n Please try again later!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}