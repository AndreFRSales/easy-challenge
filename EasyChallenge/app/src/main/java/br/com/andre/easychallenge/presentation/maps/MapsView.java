package br.com.andre.easychallenge.presentation.maps;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by andre on 15/11/17.
 */

public interface MapsView {

    void setToolbar();
    void requestLocationPermission(int permissionId);
    void updateMap(LatLng latLng, int zoom);
    void disableMapPropertiesLocation();
    void enableMapPropertiesLocation();
    void focusOnLatLng(LatLng latLng, int zoom);
}
