package br.com.andre.easychallenge.presentation.maps.presenter;

import android.os.Bundle;

import br.com.andre.easychallenge.domain.map.models.CurrentPosition;

/**
 * Created by andre on 21/11/17.
 */

public class MapsBundler {

    private Bundle bundle;
    private CurrentPosition currentPosition;
    public static final String CURRENT_POSITION_KEY = "currentPostionBundleKey";

    public MapsBundler(Bundle bundle) {
        this.bundle = bundle;
    }

    public void setCurrentPosition(CurrentPosition currentPosition) {
        this.bundle.putSerializable(CURRENT_POSITION_KEY, currentPosition);
    }


}
