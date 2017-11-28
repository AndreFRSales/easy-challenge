package br.com.andre.easychallenge.domain.map.usecases;


import br.com.andre.easychallenge.domain.base.BaseUsecase;
import br.com.andre.easychallenge.domain.map.models.Address;
import br.com.andre.easychallenge.domain.map.repository.MapsRepository;
import io.reactivex.Observable;

/**
 * Created by andre on 21/11/17.
 */

public class FindAddressUsecase extends BaseUsecase<Address, FindAddressUsecase.Params>{

    MapsRepository repository;

    public FindAddressUsecase(MapsRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<Address> createUseCase(Params params) {
        return repository.findAddress(params).map(addresses -> {
            if(addresses.size() > 0) {
                return addresses.get(0);
            } else {
                return null;
            }
        });
    }

    public static class Params {
        private String query;
        private String key;

        public Params(String query, String key) {
            this.query = query;
            this.key = key;
        }

        public String getQuery() {
            return query;
        }

        public String getKey() {
            return key;
        }
    }
}
