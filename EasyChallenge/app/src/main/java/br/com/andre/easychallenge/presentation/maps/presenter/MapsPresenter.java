package br.com.andre.easychallenge.presentation.maps.presenter;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.util.Objects;

import br.com.andre.easychallenge.presentation.base.BasePresenterContract;
import br.com.andre.easychallenge.presentation.maps.MapsView;
import br.com.andre.easychallenge.presentation.permission.PermissionPresenter;

/**
 * Created by andre on 15/11/17.
 */

public class MapsPresenter implements MapsPresenterContract {

    private final int REQUEST_LOCATION = 1;
    MapsView view;
    PermissionPresenter permissionPresenter;

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
        if(Objects.equals(PermissionPresenter.COARSE_LOCATION_REQUEST_CODE, requestCode)) {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    permissionPresenter.rejected();
                } else {
                    permissionPresenter.accepted();
                }
            }
        }
    }
}
