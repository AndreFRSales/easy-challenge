package br.com.andre.easychallenge.data.map.repository;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.List;

import br.com.andre.easychallenge.data.map.mappers.MapsMapper;
import br.com.andre.easychallenge.domain.map.models.Address;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import br.com.andre.easychallenge.domain.map.repository.MapsRepository;
import br.com.andre.easychallenge.domain.map.usecases.FindAddressUsecase;
import io.reactivex.Observable;

/**
 * Created by andre on 20/11/17.
 */

public class MapsDataRepositoryImp implements MapsRepository {

    MapsRemoteDataSource remoteDataSource;
    MapsMapper mapsMapper;

    public MapsDataRepositoryImp(MapsRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.mapsMapper = new MapsMapper();

    }

    @Override
    public Observable<CurrentPosition> getCurrentPosition(FusedLocationProviderClient fusedLocationProviderClient) {
        return remoteDataSource.getCurrentPosition(fusedLocationProviderClient).map(mapsMapper::mapToCurrentPositionModel);
    }

    @Override
    public Observable<List<Address>> findAddress(FindAddressUsecase.Params params) {
        return remoteDataSource.findAddress(params.getQuery(), params.getKey()).map(
                googleMapAddress -> mapsMapper.mapToAddressList(googleMapAddress));
    }
}
