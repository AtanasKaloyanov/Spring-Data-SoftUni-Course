package softuni.exam.models.dto.car;

import softuni.exam.models.entity.enums.CarType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportDTO {

    @Enumerated(EnumType.STRING)
    @NotNull
    private CarType carType;

    @Size(min = 2, max = 30)
    @NotNull
    private String carMake;

    @Size(min = 2, max = 30)
    @NotNull
    private String carModel;

    @Positive
    @NotNull
    private Integer year;

    @Size(min = 2, max = 30)
    @NotNull
    private String plateNumber;

    @Positive
    @NotNull
    private Integer kilometers;

    @Min(1)
    @NotNull
    private Double engine;

    public CarImportDTO() {}

    public CarImportDTO(CarType carType, String carMake, String carModel, Integer year, String plateNumber, Integer kilometers, Double engine) {
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
