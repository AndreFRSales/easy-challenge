package br.com.andre.easychallenge.data.map.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andre on 21/11/17.
 */

public class GoogleLocation {

    @SerializedName("lat")
    private double latitude;
    @SerializedName("lng")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
