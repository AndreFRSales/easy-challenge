package br.com.andre.easychallenge.data.retrofit;

import retrofit2.Retrofit;

/**
 * Created by andre on 22/11/17.
 */

public class RetrofitFactory {

    public static Retrofit createGeocodingProvider() {
        return new RetrofitProvider(ConstantBaseUrl.GEOCODING_BASE_URL).provideRetrofit();
    }

    public static Retrofit createMockApiProvider() {
        return new RetrofitProvider(ConstantBaseUrl.MOCK_API_BASE_URL).provideRetrofit();
    }
}
