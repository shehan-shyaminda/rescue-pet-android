package com.codelabs_coding.petrescue.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

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
                .withEndAction(this::askNotificationPermission)
                .start();
    }

    private void startActivity(Boolean isLogged) {
        Intent intent = new Intent(this, isLogged ? MainActivity.class : LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (!isGranted) {
                    Toast.makeText(this, "You will not be able to receive any Notifications! You can turn on notifications later from settings", Toast.LENGTH_LONG).show();
                }

                startActivity(spUtils.getBoolean(SpUtils.KEY_IS_LOGGED_IN));
            });

    private void askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                startActivity(spUtils.getBoolean(SpUtils.KEY_IS_LOGGED_IN));
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }
}