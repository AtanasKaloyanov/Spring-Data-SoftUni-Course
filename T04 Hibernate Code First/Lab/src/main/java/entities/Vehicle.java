package entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
//       Annotation for class                                Annotation for ID
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) + @GeneratedValue(strategy =
// GenerationType.TABLE) -> first diagram

//     Annotation for class                                       Annotation for ID
// @Table(name = "vehicles")
//@Inheritance(strategy = InheritanceType.JOINED) + @GeneratedValue(strategy = GenerationType.TABLE)
//-> second diagram

//@Entity
//@Table(name = "vehicles")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "type")
//
//public abstract class Vehicle {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    protected long id;
//
//    @Column(insertable = false, updatable = false)
//    @Basic
//    protected String type;

// -> third table

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")

public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(insertable = false, updatable = false)
    @Basic
    protected String type;

    @Column
    protected String model;

    @Column
    protected BigDecimal price;

    @Column(name = "fuel_type")
    protected String fuelType;

    protected Vehicle() {
    }

    protected Vehicle(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
