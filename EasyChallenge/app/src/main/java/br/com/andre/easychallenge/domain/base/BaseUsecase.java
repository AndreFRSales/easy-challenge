package br.com.andre.easychallenge.domain.base;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by andre on 21/11/17.
 */

public abstract class BaseUsecase<T, Params> {

    protected abstract Observable<T> createUseCase(Params params);

    public Observable<T> execute(Params params) {
        return this.createUseCase(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
