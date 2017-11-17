package br.com.andre.easychallenge.presentation.permission;

import android.Manifest;

/**
 * Created by andre on 16/11/17.
 */

public class PermissionPresenter {

    PermissionManagerContract permissionManager;
    PermissionView permissionView;

    public static final int FINE_LOCATION_REQUEST_CODE = 1;
    public static final String ACCESS_FINE_LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;

    public PermissionPresenter(PermissionManagerContract permissionManager, PermissionView permissionView) {
        this.permissionManager = permissionManager;
        this.permissionView = permissionView;
    }

    public void requestPermission() {
        if(permissionManager.hasPermission(ACCESS_FINE_LOCATION_PERMISSION)) {
            permissionView.permissionAccepted();
        } else {
            permissionManager.requestPermission(ACCESS_FINE_LOCATION_PERMISSION, FINE_LOCATION_REQUEST_CODE);
        }
    }

    public void accepted() {
        permissionView.permissionAccepted();
    }

    public void rejected() {
        permissionView.permissionRejected();
    }
 }
