package com.example.modelmapping.models.dto;

public class AddressDTO {

    private String city;
    private String country;



    public AddressDTO(String city, String country) {
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
}
