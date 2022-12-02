package softuni.exam.models.dto.Offer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImporWrapperDTO {
    @XmlElement(name = "offer")
    private List<OfferImporDTO> offertImportDTOs;

    public OfferImporWrapperDTO() {}

    public OfferImporWrapperDTO(List<OfferImporDTO> offertImportDTOs) {
        this.offertImportDTOs = offertImportDTOs;
    }

    public List<OfferImporDTO> getOffertImportDTOs() {
        return offertImportDTOs;
    }

    public void setOffertImportDTOs(List<OfferImporDTO> offertImportDTOs) {
        this.offertImportDTOs = offertImportDTOs;
    }
}
