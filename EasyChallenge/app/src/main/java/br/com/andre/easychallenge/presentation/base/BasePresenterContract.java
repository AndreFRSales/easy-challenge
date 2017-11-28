package br.com.andre.easychallenge.presentation.base;

import android.os.Bundle;

/**
 * Created by andre on 16/11/17.
 */

public interface BasePresenterContract {

    void start();
    void destroy();
    void saveState(Bundle outState);
    void restoreState(Bundle savedInstanceState);

}
