package com.example.modelmapping.models.dto.addresses;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryXMLDTO {

    @XmlAttribute
    private String name;

    public CountryXMLDTO() {}

    public CountryXMLDTO(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CountryXMLDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
