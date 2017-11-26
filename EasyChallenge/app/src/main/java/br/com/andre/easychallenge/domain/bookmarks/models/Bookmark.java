package br.com.andre.easychallenge.domain.bookmarks.models;

/**
 * Created by andre on 25/11/17.
 */

public class Bookmark {

    String description;
    double latitude;
    double longitude;

    public Bookmark(String description, double latitude, double longitude) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
