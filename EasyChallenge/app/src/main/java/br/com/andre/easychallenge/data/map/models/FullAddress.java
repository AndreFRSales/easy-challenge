package br.com.andre.easychallenge.data.map.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andre on 21/11/17.
 */

class FullAddress {

    @SerializedName("formatted_address")
    private String formattedAddress;
    @SerializedName("geometry")
    private Geometry geometry;

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }
}
