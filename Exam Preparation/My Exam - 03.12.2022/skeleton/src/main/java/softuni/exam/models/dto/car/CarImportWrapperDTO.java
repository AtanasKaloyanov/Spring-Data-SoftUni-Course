package softuni.exam.models.dto.car;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportWrapperDTO {

    @XmlElement(name = "car")
    private List<CarImportDTO> carImportDTOList;

    public CarImportWrapperDTO() {}

    public CarImportWrapperDTO(List<CarImportDTO> carImportDTOList) {
        this.carImportDTOList = carImportDTOList;
    }

    public List<CarImportDTO> getCarImportDTOList() {
        return carImportDTOList;
    }

    public void setCarImportDTOList(List<CarImportDTO> carImportDTOList) {
        this.carImportDTOList = carImportDTOList;
    }
}
