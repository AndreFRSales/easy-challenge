package br.com.andre.easychallenge.data.map.mappers;

import android.location.Location;

import br.com.andre.easychallenge.domain.map.models.CurrentPosition;

/**
 * Created by andre on 20/11/17.
 */

public class CurrentPositionMapper {

    public CurrentPosition mapToModel(Location location) {
        return new CurrentPosition(location.getLatitude(), location.getLongitude());
    }

}
