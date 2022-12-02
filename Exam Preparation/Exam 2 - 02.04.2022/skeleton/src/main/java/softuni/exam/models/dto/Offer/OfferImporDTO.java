package softuni.exam.models.dto.Offer;

import softuni.exam.models.dto.Apartment.ApartmentIdWrapperDTO;
import softuni.exam.models.dto.agent.AgentNameWrapperDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImporDTO {

    @Positive
    @XmlElement
    private Double price;

    @XmlElement
    private String publishedOn;

    @XmlElement(name = "apartment")
    private ApartmentIdWrapperDTO apartmentIdWrapperDTO;

    @XmlElement(name = "agent")
    private AgentNameWrapperDTO agentNameWrapperDTO;

    public OfferImporDTO() {}

    public OfferImporDTO(Double price, String publishedOn, ApartmentIdWrapperDTO apartmentIdWrapperDTO, AgentNameWrapperDTO agentNameWrapperDTO) {
        this.price = price;
        this.publishedOn = publishedOn;
        this.apartmentIdWrapperDTO = apartmentIdWrapperDTO;
        this.agentNameWrapperDTO = agentNameWrapperDTO;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }

    public ApartmentIdWrapperDTO getApartmentIdWrapperDTO() {
        return apartmentIdWrapperDTO;
    }

    public void setApartmentIdWrapperDTO(ApartmentIdWrapperDTO apartmentIdWrapperDTO) {
        this.apartmentIdWrapperDTO = apartmentIdWrapperDTO;
    }

    public AgentNameWrapperDTO getAgentNameWrapperDTO() {
        return agentNameWrapperDTO;
    }

    public void setAgentNameWrapperDTO(AgentNameWrapperDTO agentNameWrapperDTO) {
        this.agentNameWrapperDTO = agentNameWrapperDTO;
    }
}
