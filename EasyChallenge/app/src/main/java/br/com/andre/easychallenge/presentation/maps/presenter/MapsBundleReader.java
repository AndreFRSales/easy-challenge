package br.com.andre.easychallenge.presentation.maps.presenter;

import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import br.com.andre.easychallenge.domain.map.models.CurrentPosition;

/**
 * Created by andre on 21/11/17.
 */

public class MapsBundleReader {

    Bundle bundle;
    private static double LAT_DEFAULT = 0;
    private static double LONG_DEFAULT = 0;

    public MapsBundleReader(Bundle bundle) {
        this.bundle = bundle;
    }

    public CurrentPosition getCurrentPostion() {
        if(bundle.containsKey(MapsBundler.CURRENT_POSITION_KEY)) {
            return (CurrentPosition) bundle.getSerializable(MapsBundler.CURRENT_POSITION_KEY);
        } else {
            return new CurrentPosition(LAT_DEFAULT,LONG_DEFAULT);
        }
    }
}
