package br.com.andre.easychallenge.domain.map.models;

import java.io.Serializable;

/**
 * Created by andre on 20/11/17.
 */

public class CurrentPosition implements Serializable {

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
