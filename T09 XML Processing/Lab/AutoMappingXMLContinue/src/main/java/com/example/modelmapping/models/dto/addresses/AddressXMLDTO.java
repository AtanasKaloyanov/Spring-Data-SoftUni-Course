package com.example.modelmapping.models.dto.addresses;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressXMLDTO{

    @XmlAttribute
    private int id;

    @XmlElement
    private String country;
    @XmlElement
    private String city;

    public AddressXMLDTO() {}

    public AddressXMLDTO(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "AddressXMLDTO{" +
                "id=" + id +
                ", country=" + country +
                ", city='" + city + '\'' +
                '}';
    }
}
