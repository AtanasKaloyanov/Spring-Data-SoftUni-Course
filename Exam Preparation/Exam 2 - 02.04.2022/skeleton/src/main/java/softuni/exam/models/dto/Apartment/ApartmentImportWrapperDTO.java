package softuni.exam.models.dto.Apartment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "apartments")
public class ApartmentImportWrapperDTO {
    @XmlElement(name = "apartment")
    private List<ApartmentImportDTO> apartmentDTOs;

    public ApartmentImportWrapperDTO() {}

    public ApartmentImportWrapperDTO(List<ApartmentImportDTO> apartmentDTOs) {
        this.apartmentDTOs = apartmentDTOs;
    }

    public List<ApartmentImportDTO> getApartmentDTOs() {
        return apartmentDTOs;
    }

    public void setApartmentDTOs(List<ApartmentImportDTO> apartmentDTOs) {
        this.apartmentDTOs = apartmentDTOs;
    }
}



//<apartment>
//        <apartmentType>three_rooms</apartmentType>
//        <area>63.52</area>
//        <town>Sofia</town>
//    </apartment>