package com.example.modelmapping.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address extends BaseEntity{

    public Address() {}
    @Column
    private String city;

    @Column
    private String country;


    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
