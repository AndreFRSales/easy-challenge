package br.com.andre.easychallenge.presentation.maps.presenter;

import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;

import br.com.andre.easychallenge.presentation.base.BasePresenterContract;

/**
 * Created by andre on 16/11/17.
 */

public interface MapsPresenterContract extends BasePresenterContract{

    void setupAcceptedMap(FusedLocationProviderClient fusedLocationProviderClient);
    void setupRejectedMap();
    void updateLastPosition(LatLng latLng);
    void focusOnLastPosition();
    void checkPermission(int[] grantResults, int requestCode);
    void findAddress(String query, String key);
    void saveBookmark(String dialogResult);
    void redirectMenuItem(int menuItemId);
    void loadPosition(Bundle bundle);

}
