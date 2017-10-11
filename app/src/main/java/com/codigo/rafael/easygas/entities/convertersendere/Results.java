package com.codigo.rafael.easygas.entities.convertersendere;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by shang on 10/10/2017.
 */

public class Results implements Serializable {

    private String place_id;

    private String formatted_address;

    private String[] types;

    private AddressComponents addressComponents;

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }


    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public AddressComponents getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(AddressComponents addressComponents) {
        this.addressComponents = addressComponents;
    }

    @Override
    public String toString() {
        return "Results{" +
                "place_id='" + place_id + '\'' +
                ", formatted_address='" + formatted_address + '\'' +
                ", types=" + Arrays.toString(types) +
                ", addressComponents=" + addressComponents +
                '}';
    }
}
