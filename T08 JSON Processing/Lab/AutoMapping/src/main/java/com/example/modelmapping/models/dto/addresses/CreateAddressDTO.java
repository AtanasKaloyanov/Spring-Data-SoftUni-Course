package com.example.modelmapping.models.dto.addresses;

import com.google.gson.annotations.Expose;

public class CreateAddressDTO {

    @Expose
    private String city;

    @Expose
    private String country;

    public CreateAddressDTO() {}

    public CreateAddressDTO(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
