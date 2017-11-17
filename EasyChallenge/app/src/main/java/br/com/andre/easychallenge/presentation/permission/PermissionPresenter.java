package br.com.andre.easychallenge.presentation.permission;

import android.Manifest;

/**
 * Created by andre on 16/11/17.
 */

public class PermissionPresenter {

    PermissionManagerContract permissionManager;
    PermissionView permissionView;

    public static final int COARSE_LOCATION_REQUEST_CODE = 1;

    public PermissionPresenter(PermissionManagerContract permissionManager, PermissionView permissionView) {
        this.permissionManager = permissionManager;
        this.permissionView = permissionView;
    }

    public void requestPermission() {
        if(permissionManager.hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            permissionView.permissionAccepted();
        } else {
            permissionManager.requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION, COARSE_LOCATION_REQUEST_CODE);
        }
    }

    public void accepted() {
        permissionView.permissionAccepted();
    }

    public void rejected() {
        permissionView.permissionRejected();
    }
 }
