package br.com.andre.easychallenge.presentation.maps.presenter;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;

import br.com.andre.easychallenge.presentation.base.BasePresenterContract;

/**
 * Created by andre on 16/11/17.
 */

public interface MapsPresenterContract extends BasePresenterContract{

    void setupAcceptedMap(FusedLocationProviderClient fusedLocationProviderClient);
    void setupRejectedMap();
}
