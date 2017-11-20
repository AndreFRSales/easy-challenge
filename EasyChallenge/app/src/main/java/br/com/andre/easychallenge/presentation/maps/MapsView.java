package br.com.andre.easychallenge.presentation.maps;

/**
 * Created by andre on 15/11/17.
 */

public interface MapsView {

    void setToolbar();
    void requestLocationPermission(int permissionId);
    void updateMap(double latitude, double longitude, int zoom);
    void disableMapPropertiesLocation();
    void enableMapPropertiesLocation();
}
