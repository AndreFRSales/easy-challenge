package br.com.andre.easychallenge.domain.map.usecases;

import com.google.android.gms.location.FusedLocationProviderClient;

import br.com.andre.easychallenge.domain.base.BaseUsecase;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import br.com.andre.easychallenge.domain.map.repository.MapsRepository;
import io.reactivex.Observable;

/**
 * Created by andre on 20/11/17.
 */

public class GetCurrentPositionUsecase extends BaseUsecase<CurrentPosition, FusedLocationProviderClient>{

    MapsRepository repository;

    public GetCurrentPositionUsecase(MapsRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<CurrentPosition> createUseCase(FusedLocationProviderClient fusedLocationProviderClient) {
        return repository.getCurrentPosition(fusedLocationProviderClient);
    }

}
