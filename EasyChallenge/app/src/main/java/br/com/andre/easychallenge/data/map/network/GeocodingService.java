package br.com.andre.easychallenge.data.map.network;


import br.com.andre.easychallenge.data.map.models.GoogleMapAddress;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by andre on 22/11/17.
 */

public interface GeocodingService {

    @GET("maps/api/geocode/json")
    Observable<GoogleMapAddress> getTrends(@Query("address") String address, @Query("key") String key);

}
