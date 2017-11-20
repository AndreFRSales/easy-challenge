package br.com.andre.easychallenge.presentation.maps.presenter;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.gcm.Task;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;

import java.util.Objects;

import br.com.andre.easychallenge.presentation.maps.MapsView;
import br.com.andre.easychallenge.presentation.permission.PermissionPresenter;

/**
 * Created by andre on 15/11/17.
 */

public class MapsPresenter implements MapsPresenterContract {

    private final int REQUEST_LOCATION = 1;
    private MapsView view;
    private PermissionPresenter permissionPresenter;
    private boolean permissionGranted;
    private Location lastKnownLocation;
    private int DEFAULT_ZOOM = 18;

    public MapsPresenter(MapsView view, PermissionPresenter permissionPresenter) {
        this.view = view;
        this.permissionPresenter = permissionPresenter;
    }

    @Override
    public void start() {
        view.requestLocationPermission(REQUEST_LOCATION);
        view.setToolbar();
        permissionPresenter.requestPermission();
    }

    @Override
    public void showLoading() {

    }

    public void checkPermission(int[] grantResults, int requestCode) {
        if(Objects.equals(PermissionPresenter.FINE_LOCATION_REQUEST_CODE, requestCode)) {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    permissionPresenter.rejected();
                } else {
                    permissionGranted = true;
                    permissionPresenter.accepted();
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void setupAcceptedMap(FusedLocationProviderClient fusedLocationProviderClient) {
        permissionGranted = true;
        view.enableMapPropertiesLocation();
        getDeviceLocation(fusedLocationProviderClient);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void setupRejectedMap() {
        view.disableMapPropertiesLocation();
        lastKnownLocation = null;
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation(FusedLocationProviderClient fusedLocationProviderClient) {
        if(permissionGranted) {
            com.google.android.gms.tasks.Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task task) {
                    if (task.isSuccessful()) {
                        lastKnownLocation = (Location) task.getResult();
                        view.updateMap(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), DEFAULT_ZOOM);
                    } else {
                        view.updateMap(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), DEFAULT_ZOOM);
                        view.disableMapPropertiesLocation();
                    }
                }
            });
        }

    }

}
