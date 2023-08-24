package com.codelabs_coding.petrescue.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.utils.SpUtils;

public class LauncherActivity extends BaseActivity {

    private LinearLayout llPetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        llPetView = findViewById(R.id.ll_pet_view);

        llPetView.setAlpha(0f);

        llPetView.animate()
                .alpha(1f)
                .setDuration(2000)
                .withEndAction(() -> startActivity(spUtils.getBoolean(SpUtils.KEY_IS_LOGGED_IN)))
                .start();
    }

    private void startActivity(Boolean isLogged) {
        Intent intent = new Intent(this, isLogged ? MainActivity.class : LoginActivity.class);
        startActivity(intent);
        finish();
    }
}