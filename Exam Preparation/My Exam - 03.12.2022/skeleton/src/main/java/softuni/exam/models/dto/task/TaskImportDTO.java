package softuni.exam.models.dto.task;

import softuni.exam.models.dto.car.CarWrapperDTO;
import softuni.exam.models.dto.mechanic.MechanicWrapperDTO;
import softuni.exam.models.dto.part.PartWrapperDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskImportDTO {

    @Positive
    @XmlElement
    private Double price;

    @XmlElement
    private String date;

    @XmlElement
    private MechanicWrapperDTO mechanic;

    @XmlElement
    private PartWrapperDTO part;

    @XmlElement
    private CarWrapperDTO car;


    public TaskImportDTO() {}

    public TaskImportDTO(Double price, String date, MechanicWrapperDTO mechanic, PartWrapperDTO part, CarWrapperDTO car) {
        this.price = price;
        this.date = date;
        this.mechanic = mechanic;
        this.part = part;
        this.car = car;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MechanicWrapperDTO getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicWrapperDTO mechanic) {
        this.mechanic = mechanic;
    }

    public PartWrapperDTO getPart() {
        return part;
    }

    public void setPart(PartWrapperDTO part) {
        this.part = part;
    }

    public CarWrapperDTO getCar() {
        return car;
    }

    public void setCar(CarWrapperDTO car) {
        this.car = car;
    }
}
