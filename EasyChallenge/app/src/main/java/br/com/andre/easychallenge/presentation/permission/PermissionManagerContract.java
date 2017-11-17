package br.com.andre.easychallenge.presentation.permission;

/**
 * Created by andre on 16/11/17.
 */

public interface PermissionManagerContract {
    boolean hasPermission(String permission);
    void requestPermission(String permission, int requestCode);
}
