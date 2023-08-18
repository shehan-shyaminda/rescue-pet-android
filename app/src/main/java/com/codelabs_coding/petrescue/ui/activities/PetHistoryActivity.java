package com.codelabs_coding.petrescue.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.models.UserModel;
import com.codelabs_coding.petrescue.ui.adapters.PetHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class PetHistoryActivity extends AppCompatActivity {

    private List<UserModel.User.Pet.LocationHistory> contents = new ArrayList<>();
    private RecyclerView rvList;
    private PetHistoryAdapter petHistoryAdapter;
    private String petName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_history);
        rvList = findViewById(R.id.rv_list);
        petName = this.getIntent().getStringExtra("petName");
        contents = (List<UserModel.User.Pet.LocationHistory>) this.getIntent().getSerializableExtra("petHistory");
        if (!contents.isEmpty()) {
            initAdapter();
        } else {
            Toast.makeText(PetHistoryActivity.this, "No History founded for the Pet!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);
        petHistoryAdapter = new PetHistoryAdapter(contents, petName);
        rvList.setAdapter(petHistoryAdapter);
        petHistoryAdapter.notifyDataSetChanged();
    }
}