package br.com.andre.easychallenge.data.map.repository;

import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;

import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import io.reactivex.Observable;

/**
 * Created by andre on 20/11/17.
 */

public interface MapsRemoteDataSource {

    Observable<Location> getCurrentPosition(FusedLocationProviderClient fusedLocationProviderClient);

}
