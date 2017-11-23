package br.com.andre.easychallenge.data.map.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andre on 21/11/17.
 */

public class GoogleMapAddress {

    @SerializedName("results")
    List<FullAddress> fullAddressList;

    public List<FullAddress> getFullAddressList() {
        return fullAddressList;
    }
}
