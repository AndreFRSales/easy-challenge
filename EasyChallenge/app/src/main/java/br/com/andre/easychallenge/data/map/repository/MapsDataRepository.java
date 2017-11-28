package br.com.andre.easychallenge.data.map.repository;

import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;

import br.com.andre.easychallenge.data.map.models.GoogleMapAddress;
import br.com.andre.easychallenge.domain.map.usecases.FindAddressUsecase;
import io.reactivex.Observable;

/**
 * Created by andre on 22/11/17.
 */

public interface MapsDataRepository {

    Observable<Location> getCurrentPosition(FusedLocationProviderClient fusedLocationProviderClient);
    Observable<GoogleMapAddress> findAddress(FindAddressUsecase.Params query);

}
