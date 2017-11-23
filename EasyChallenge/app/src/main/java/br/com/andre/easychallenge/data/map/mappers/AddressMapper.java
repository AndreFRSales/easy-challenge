package br.com.andre.easychallenge.data.map.mappers;

import br.com.andre.easychallenge.data.map.models.FullAddress;
import br.com.andre.easychallenge.domain.map.models.Address;

/**
 * Created by andre on 22/11/17.
 */

public class AddressMapper {

    public Address mapToModel(FullAddress fullAddress) {
        double latitude = fullAddress.getGeometry().getLocation().getLatitude();
        double longitude = fullAddress.getGeometry().getLocation().getLongitude();
        return new Address(fullAddress.getFormattedAddress(), latitude, longitude);
    }

}
