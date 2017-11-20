package br.com.andre.easychallenge.domain.map.models;

/**
 * Created by andre on 20/11/17.
 */

public class CurrentPosition {

    double latitude;
    double longitude;

    public CurrentPosition(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
