package softuni.exam.models.dto.Apartment;

import softuni.exam.models.entity.Town;
import softuni.exam.models.entity.enums.ApartmentType;

import javax.persistence.Column;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentImportDTO {

    @XmlElement
    private String apartmentType;

    @Min(40)
    @XmlElement
    private Double area;

    @XmlElement
    private String town;

    public ApartmentImportDTO() {}

    public ApartmentImportDTO(String apartmentType, Double area, String town) {
        this.apartmentType = apartmentType;
        this.area = area;
        this.town = town;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}

//<apartment>
//        <apartmentType>three_rooms</apartmentType>
//        <area>63.52</area>
//        <town>Sofia</town>
//    </apartment>