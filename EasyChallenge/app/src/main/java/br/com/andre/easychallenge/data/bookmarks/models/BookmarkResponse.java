package br.com.andre.easychallenge.data.bookmarks.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andre on 25/11/17.
 */

public class BookmarkResponse {

    @SerializedName("name")
    private String name;
    @SerializedName("longitude")
    private double latitude;
    @SerializedName("latitude")
    private double longitude;

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
