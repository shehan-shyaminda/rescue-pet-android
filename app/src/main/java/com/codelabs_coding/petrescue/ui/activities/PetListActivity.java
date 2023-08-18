package com.codelabs_coding.petrescue.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codelabs_coding.petrescue.R;

public class PetListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);
    }
}