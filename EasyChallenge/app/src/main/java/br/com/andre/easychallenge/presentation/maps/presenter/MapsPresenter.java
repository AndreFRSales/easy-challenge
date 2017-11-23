package br.com.andre.easychallenge.presentation.maps.presenter;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Objects;

import br.com.andre.easychallenge.R;
import br.com.andre.easychallenge.domain.map.models.Address;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import br.com.andre.easychallenge.domain.map.repository.MapsRepository;
import br.com.andre.easychallenge.domain.map.usecases.FindAddressUsecase;
import br.com.andre.easychallenge.domain.map.usecases.GetCurrentPositionUsecase;
import br.com.andre.easychallenge.presentation.maps.MapsView;
import br.com.andre.easychallenge.presentation.permission.PermissionPresenter;
import io.reactivex.disposables.Disposable;

/**
 * Created by andre on 15/11/17.
 */

public class MapsPresenter implements MapsPresenterContract {

    private final int REQUEST_LOCATION = 1;
    private MapsView view;
    private PermissionPresenter permissionPresenter;
    private boolean permissionGranted;
    private CurrentPosition lastKnownLocation;
    private int DEFAULT_ZOOM = 18;
    GetCurrentPositionUsecase getCurrentPositionUsecase;
    FindAddressUsecase findAddressUsecase;
    ArrayList<Disposable> disposables;

    public MapsPresenter(MapsView view, PermissionPresenter permissionPresenter, MapsRepository repository) {
        this.view = view;
        this.permissionPresenter = permissionPresenter;
        setupUsecases(repository);
        disposables = new ArrayList<>();
    }

    private void setupUsecases(MapsRepository repository) {
        getCurrentPositionUsecase = new GetCurrentPositionUsecase(repository);
        findAddressUsecase = new FindAddressUsecase(repository);
    }

    @Override
    public void start() {
        view.requestLocationPermission(REQUEST_LOCATION);
        view.setToolbar();
        if(lastKnownLocation == null) {
            permissionPresenter.requestPermission();
        } else {
            view.enableMapPropertiesLocation();
            view.updateMap(createLatLng(lastKnownLocation), DEFAULT_ZOOM);
        }
    }

    @Override
    public void destroy() {
        for (Disposable disposable: disposables) {
            if(disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    }

    @Override
    public void saveState(Bundle outState) {
        MapsBundler mapsBundler = new MapsBundler(outState);
        mapsBundler.setCurrentPosition(lastKnownLocation);
    }

    @Override
    public void restoreState(Bundle savedInstanceState) {
        MapsBundleReader reader = new MapsBundleReader(savedInstanceState);
        lastKnownLocation = reader.getCurrentPostion();
    }

    @Override
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

    @Override
    public void findAddress(String query, String key) {
        disposables.add(findAddressUsecase.execute(new FindAddressUsecase.Params(query, key))
        .subscribe(
                address -> {
                    view.hideKeyboard();
                    view.updateMap(createLatLng(address), DEFAULT_ZOOM);
                },
                error -> {
                    view.hideKeyboard();
                    view.showErrorSnackBar(R.string.maps_menu_search_didnt_found);
                }
        ));
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

    @Override
    public void updateLastPosition(LatLng latLng) {
        lastKnownLocation = new CurrentPosition(latLng.latitude, latLng.longitude);
        view.focusOnLatLng(latLng, DEFAULT_ZOOM);
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation(FusedLocationProviderClient fusedLocationProviderClient) {
        if(permissionGranted) {
            disposables.add(getCurrentPositionUsecase.execute(fusedLocationProviderClient)
            .subscribe(currentPosition -> {
                lastKnownLocation = currentPosition;
                view.updateMap(createLatLng(lastKnownLocation), DEFAULT_ZOOM);
            }, error -> {
                view.updateMap(createLatLng(lastKnownLocation), DEFAULT_ZOOM);
                view.disableMapPropertiesLocation();
            }));
        }
    }

    private LatLng createLatLng(CurrentPosition currentPosition) {
        return new LatLng(currentPosition.getLatitude(), currentPosition.getLongitude());
    }

    private LatLng createLatLng(Address address) {
        return new LatLng(address.getLatitude(), address.getLongitude());
    }

}
