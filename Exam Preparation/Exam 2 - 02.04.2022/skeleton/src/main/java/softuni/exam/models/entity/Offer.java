package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{

    @Positive
    @Column()
    private Double price;

    @Column(name = "published_on")
    private LocalDate publishedOn;

    @ManyToOne
    private Agent agent;

    @ManyToOne
    private Apartment apartment;

    public Offer() {}

    public Offer(Double price, LocalDate publishedOn, Agent agent, Apartment apartment) {
        this.price = price;
        this.publishedOn = publishedOn;
        this.agent = agent;
        this.apartment = apartment;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
