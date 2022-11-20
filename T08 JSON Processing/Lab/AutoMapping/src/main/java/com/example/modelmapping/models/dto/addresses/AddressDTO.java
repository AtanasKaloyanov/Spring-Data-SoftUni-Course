package com.example.modelmapping.models.dto.addresses;

import com.google.gson.annotations.Expose;

public class AddressDTO extends CreateAddressDTO{

    @Expose
    private long id;

    public AddressDTO() {
        super();
    }

    public AddressDTO(String city, String country) {
        super(city, country);
    }
}
