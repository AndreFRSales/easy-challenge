package br.com.andre.easychallenge.domain.map.repository;

import com.google.android.gms.location.FusedLocationProviderClient;

import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import io.reactivex.Observable;

/**
 * Created by andre on 20/11/17.
 */

public interface MapsRepository {

    Observable<CurrentPosition> getCurrentPosition(FusedLocationProviderClient fusedLocationProviderClient);
}
