package br.com.andre.easychallenge.data.map.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by andre on 21/11/17.
 */

class Geometry {

    @SerializedName("location")
    GoogleLocation location;
}
