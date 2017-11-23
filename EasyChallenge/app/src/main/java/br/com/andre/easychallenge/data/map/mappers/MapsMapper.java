package br.com.andre.easychallenge.data.map.mappers;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

import br.com.andre.easychallenge.data.map.models.FullAddress;
import br.com.andre.easychallenge.data.map.models.GoogleMapAddress;
import br.com.andre.easychallenge.domain.map.models.Address;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;


/**
 * Created by andre on 20/11/17.
 */

public class MapsMapper {

    public CurrentPosition mapToCurrentPositionModel(Location location) {
        return new CurrentPosition(location.getLatitude(), location.getLongitude());
    }

    public List<Address> mapToAddressList(GoogleMapAddress googleMapAddress) {
        ArrayList<Address> addressList = new ArrayList<>();
        for (FullAddress fullAddress: googleMapAddress.getFullAddressList()) {
            addressList.add(new Address(fullAddress.getFormattedAddress(), fullAddress.getGeometry().getLocation().getLatitude(),
                    fullAddress.getGeometry().getLocation().getLongitude()));
        }

        return addressList;
    }

}
