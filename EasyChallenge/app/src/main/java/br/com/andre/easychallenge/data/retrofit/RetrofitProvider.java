package br.com.andre.easychallenge.data.retrofit;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andre on 22/11/17.
 */

public class RetrofitProvider {

    String baseUrl;

    public RetrofitProvider(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(provideGsonFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private Converter.Factory provideGsonFactory() {
        return GsonConverterFactory.create();
    }

}
