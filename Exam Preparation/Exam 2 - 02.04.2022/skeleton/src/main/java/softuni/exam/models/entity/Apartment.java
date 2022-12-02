package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.ApartmentType;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "apartments")
public class Apartment extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "apartment_type")
    private ApartmentType apartmentType;

    @Column
    @Min(40)
    private Double area;

    @ManyToOne
    private Town town;

    public Apartment() {}

    public Apartment(ApartmentType apartmentType, Double area, Town town) {
        this.apartmentType = apartmentType;
        this.area = area;
        this.town = town;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
