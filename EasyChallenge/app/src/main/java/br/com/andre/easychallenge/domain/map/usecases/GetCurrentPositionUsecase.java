package br.com.andre.easychallenge.domain.map.usecases;

import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;

import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import br.com.andre.easychallenge.domain.map.repository.MapsRepository;
import io.reactivex.Observable;

/**
 * Created by andre on 20/11/17.
 */

public class GetCurrentPositionUsecase {

    MapsRepository repository;

    public GetCurrentPositionUsecase(MapsRepository repository) {
        this.repository = repository;
    }

    public Observable<CurrentPosition> execute(FusedLocationProviderClient fusedLocationProviderClient) {
        return repository.getCurrentPosition(fusedLocationProviderClient);
    }
}
