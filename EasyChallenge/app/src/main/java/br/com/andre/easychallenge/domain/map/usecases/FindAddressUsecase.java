package br.com.andre.easychallenge.domain.map.usecases;


import br.com.andre.easychallenge.domain.BaseUsecase;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import br.com.andre.easychallenge.domain.map.repository.MapsRepository;
import io.reactivex.Observable;

/**
 * Created by andre on 21/11/17.
 */

public class FindAddressUsecase extends BaseUsecase<CurrentPosition, String>{

    MapsRepository repository;

    public FindAddressUsecase(MapsRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<CurrentPosition> createUseCase(String query) {
        return repository.findAddress(query);
    }
}
