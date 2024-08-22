package com.codelabs_coding.petrescue.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.codelabs_coding.petrescue.R;
import com.codelabs_coding.petrescue.models.UserModel;
import com.codelabs_coding.petrescue.utils.CommonUtils;
import com.codelabs_coding.petrescue.utils.MsgEvent;
import com.codelabs_coding.petrescue.utils.RxBus;
import com.codelabs_coding.petrescue.utils.RxSubscriptions;
import com.codelabs_coding.petrescue.utils.SpUtils;
import com.codelabs_coding.petrescue.utils.dialogUtils.DialogUtils;
import com.codelabs_coding.petrescue.utils.networkUtils.RetrofitCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainActivity extends BaseActivity implements OnMapReadyCallback {

    private static final String TAG = "MainActivity";
    FloatingActionButton fabMenu, fabRelocate, fabLogout;
    private long lastBackPressTime = 0;
    private GoogleMap mMap;
    private TextView spinner;
    private ImageView locatePet;
    private ImageView ivAddPet;
    private ImageView ivPetHistory;
    private LinearLayout fabSubMenu;
    private static UserModel.User userModel;
    //    private List<UserModel.User.Pet> petList = new ArrayList<>();
    private UserModel.User.Pet selectedPet;
    private boolean isSubMenuVisible = false;
    private int sPosition = RecyclerView.NO_POSITION;
    private Disposable mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.ll_selected_pet);
        locatePet = findViewById(R.id.ic_locate_pet);
        ivAddPet = findViewById(R.id.iv_add_pet);
        ivPetHistory = findViewById(R.id.iv_pet_history);
        fabMenu = findViewById(R.id.fabMenu);
        fabRelocate = findViewById(R.id.fabRelocate);
        fabLogout = findViewById(R.id.fabLogout);
        fabSubMenu = findViewById(R.id.fabSubMenu);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        init();

        spinner.setOnClickListener(v -> showCustomBottomSheet());
//        ivAddPet.setOnClickListener(v -> CommonUtils.startActivity(MainActivity.this, AddPetActivity.class));
//        ivPetHistory.setOnClickListener(v -> {
//            if (sPosition == -1 || petList.get(sPosition).getLocationHistory() == null) return;
//            CommonUtils.startActivity(MainActivity.this, PetHistoryActivity.class, BundleUtils.getBundleSerializableString("petName", spinner.getText().toString(), "petHistory", selectedPet.getLocationHistory()));
//        });
//        locatePet.setOnClickListener(v -> {
//            if ((petList != null && petList.isEmpty()) || sPosition == -1) return;
//            assert petList != null;
//            focusPetLocation(CommonUtils.formatDouble(petList.get(sPosition).getPetLatitude()), CommonUtils.formatDouble(petList.get(sPosition).getPetLongitude()));
//        });
        fabMenu.setOnClickListener(v -> toggleSubMenu());
        fabLogout.setOnClickListener(v -> logout());
        fabRelocate.setOnClickListener(v -> {
            DialogUtils.showCancelOkDialog(MainActivity.this, "Change Owner Location", "Are you sure you want to Re-Locate?", "Yes", "Cancel", new DialogUtils.OnDialogClickListener() {
                @Override
                public void onPositiveClick() {
                    checkForPermissions();
                }

                @Override
                public void onNegativeClick() {

                }
            });
        });
    }

    private void init() {
        userModel = new Gson().fromJson(spUtils.getString(SpUtils.KEY_USER_OBJECT), UserModel.User.class);
        spinner.setText((null != userModel.getPets() && !userModel.getPets().isEmpty()) ? userModel.getPets().get(0).getPetsNickname() : "None");
    }

    private void toggleSubMenu() {
        if (!isSubMenuVisible) {
            fabSubMenu.setVisibility(View.VISIBLE);
            fabSubMenu.animate().translationY(0f).alpha(1f);
        } else {
            fabSubMenu.setVisibility(View.GONE);
            fabSubMenu.animate().translationY(0f).alpha(1f);
        }

        isSubMenuVisible = !isSubMenuVisible;
    }

    private void logout() {
        DialogUtils.showCancelOkDialog(this, "Exit App", "Are you sure you want to exit?", "Yes", "Cancel", new DialogUtils.OnDialogClickListener() {
            @Override
            public void onPositiveClick() {
                spUtils.clear();
                CommonUtils.jumpToActivityWithClearStack(MainActivity.this, LoginActivity.class);
            }

            @Override
            public void onNegativeClick() {

            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        checkForPermissions();
    }

    private void checkForPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            getLastLocation();
        }
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    LatLng selfLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(selfLocation).title("Me"));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(selfLocation).zoom(18).build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    updateOwnerLocation(location.getLatitude(), location.getLongitude());
                }
            });
        }
    }

    private void focusPetLocation(Double latitude, Double longitude) {
        LatLng petLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(petLocation).title("Your Pet"));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(petLocation).zoom(18).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void updateOwnerLocation(Double latitude, Double longitude) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", spUtils.getString(SpUtils.KEY_USERID));
        map.put("userLatitude", latitude);
        map.put("userLongitude", longitude);
        String json = new Gson().toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofitProvider.makeRequest(apiService.UpdateOwnerLocation("Bearer " + spUtils.getString(SpUtils.KEY_AUTH_TOKEN), requestBody), new RetrofitCallback<UserModel.User>() {
            @Override
            public void onSuccess(UserModel.User response) {
                loadingDialog.hideDialog();
                spUtils.saveString(SpUtils.KEY_USER_OBJECT, new Gson().toJson(response));
                init();
                fabSubMenu.setVisibility(View.GONE);
                fabSubMenu.animate().translationY(0f).alpha(1f);
                isSubMenuVisible = !isSubMenuVisible;
            }

            @Override
            public void onError(int statusCode, String errorMessage) {
                loadingDialog.hideDialog();
                Log.e(TAG, "Request failed with code: " + statusCode);
                Toast.makeText(MainActivity.this, "Something went wrong.\n Please try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateOwner() {
        retrofitProvider.makeRequest(apiService.GetMySelf("Bearer " + spUtils.getString(SpUtils.KEY_AUTH_TOKEN)), new RetrofitCallback<UserModel.User>() {
            @Override
            public void onSuccess(UserModel.User response) {
                loadingDialog.hideDialog();
                spUtils.saveString(SpUtils.KEY_USER_OBJECT, new Gson().toJson(response));
                init();
            }

            @Override
            public void onError(int statusCode, String errorMessage) {
                loadingDialog.hideDialog();
                Log.e(TAG, "Request failed with code: " + statusCode);
                Toast.makeText(MainActivity.this, "Something went wrong.\n Please try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showCustomBottomSheet() {
        if (null == userModel.getPets() || userModel.getPets().isEmpty()) {
            Toast.makeText(this, "Please add a pet first!", Toast.LENGTH_SHORT).show();
            return;
        }
        List<Map<String, String>> petsNames = userModel.getPets().stream().map(pet -> {
            Map<String, String> nameAndType = new HashMap<>();
            nameAndType.put("petName", pet.getPetsNickname());
            nameAndType.put("petType", (pet.getPetsType().equals("1")) ? "Dog" : "Cat");
            return nameAndType;
        }).collect(Collectors.toList());

        DialogUtils.showSimpleBottomMenuDialog(this, petsNames, position -> {
            spinner.setText(userModel.getPets().get(position).getPetsNickname());
            sPosition = position;
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBackPressTime < DOUBLE_PRESS_EXIT_TIME) {
            super.onBackPressed();
        } else {
            lastBackPressTime = currentTime;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void registerRxBus() {
        super.registerRxBus();
        mSubscription = RxBus.getDefault().toObservable(MsgEvent.class).subscribe(msgEvent -> {
            switch (msgEvent.getBusinessKey()) {
                case MsgEvent.UPDATE_PET_LIST:
                    updateOwner();
                    break;
                case MsgEvent.UPDATE_USER_INFO:
                    break;
            }
        });

        RxSubscriptions.add(mSubscription);
    }

    @Override
    public void removeRxBus() {
        super.removeRxBus();
        RxSubscriptions.remove(mSubscription);
    }
}
