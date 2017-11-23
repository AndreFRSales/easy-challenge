package br.com.andre.easychallenge.data.map.repository;

import android.annotation.SuppressLint;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;

import br.com.andre.easychallenge.data.map.models.GoogleMapAddress;
import br.com.andre.easychallenge.data.map.network.GeocodingService;
import br.com.andre.easychallenge.data.retrofit.RetrofitFactory;
import io.reactivex.Observable;

/**
 * Created by andre on 20/11/17.
 */

public class MapsRemoteDataSourceImp implements MapsRemoteDataSource {

    GeocodingService geocodingService;

    public MapsRemoteDataSourceImp() {
        geocodingService = RetrofitFactory.createGeocodingProvider().create(GeocodingService.class);

    }

    @Override
    @SuppressLint("MissingPermission")
    public Observable<Location> getCurrentPosition(FusedLocationProviderClient fusedLocationProviderClient) {
        return Observable.create(emitter -> {
            com.google.android.gms.tasks.Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    emitter.onNext(task.getResult());
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception());
                }
            });
        });
    }

    @Override
    public Observable<GoogleMapAddress> findAddress(String query, String key) {
        return geocodingService.getTrends(query, key)
                .doOnError(Observable::error);
    }
}
