package br.com.andre.easychallenge.presentation.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by andre on 16/11/17.
 */

public class PermissionManager implements PermissionManagerContract {

    private Activity activity;

    public PermissionManager(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void requestPermission(String permission, int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[] { permission }, requestCode);
    }
}
