package br.com.andre.easychallenge.domain.map.models;

/**
 * Created by andre on 22/11/17.
 */

public class Address {

    String address;
    double latitude;
    double longitude;

    public Address(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
