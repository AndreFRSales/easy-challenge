package br.com.andre.easychallenge.data.map.repository;

import com.google.android.gms.location.FusedLocationProviderClient;

import br.com.andre.easychallenge.data.map.mappers.CurrentPositionMapper;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import br.com.andre.easychallenge.domain.map.repository.MapsRepository;
import io.reactivex.Observable;

/**
 * Created by andre on 20/11/17.
 */

public class MapsDataRepository implements MapsRepository {

    MapsRemoteDataSource remoteDataSource;
    CurrentPositionMapper mapper;

    public MapsDataRepository(MapsRemoteDataSource remoteDataSource, CurrentPositionMapper mapper) {
        this.remoteDataSource = remoteDataSource;
        this.mapper = mapper;
    }

    @Override
    public Observable<CurrentPosition> getCurrentPosition(FusedLocationProviderClient fusedLocationProviderClient) {
        return remoteDataSource.getCurrentPosition(fusedLocationProviderClient).map(mapper::mapToModel);
    }

    @Override
    public Observable<CurrentPosition> findAddress(String query) {
        return null;
    }
}
