package br.com.andre.easychallenge.domain.map.repository;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.List;

import br.com.andre.easychallenge.domain.map.models.Address;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import br.com.andre.easychallenge.domain.map.usecases.FindAddressUsecase;
import io.reactivex.Observable;

/**
 * Created by andre on 20/11/17.
 */

public interface MapsRepository {

    Observable<CurrentPosition> getCurrentPosition(FusedLocationProviderClient fusedLocationProviderClient);
    Observable<List<Address>> findAddress(FindAddressUsecase.Params query);
}
