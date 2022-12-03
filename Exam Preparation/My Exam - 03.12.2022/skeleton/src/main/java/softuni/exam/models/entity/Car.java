package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.CarType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity{


    @Enumerated(EnumType.STRING)
    @Column(name = "car_type")
    private CarType carType;

    @Size(min = 2, max = 30)
    @Column(name = "car_make")
    private String carMake;

    @Size(min = 2, max = 30)
    @Column(name = "car_model")
    private String carModel;

    @Positive
    @Column
    private Integer year;

    @Size(min = 2, max = 30)
    @Column(name = "plate_number", unique = true)
    private String plateNumber;

    @Positive
    private Integer kilometers;

    @Min(1)
    private Double engine;

    public Car() {}

    public Car(CarType carType, String carMake, String carModel, Integer year, String plateNumber, Integer kilometers, Double engine) {
        this.carType = carType;
        this.carMake = carMake;
        this.carModel = carModel;
        this.year = year;
        this.plateNumber = plateNumber;
        this.kilometers = kilometers;
        this.engine = engine;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public Double getEngine() {
        return engine;
    }

    public void setEngine(Double engine) {
        this.engine = engine;
    }
}
