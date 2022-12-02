package exam.model.dto.shop;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopImportWrapperDTO {

    @XmlElement(name = "shop")
    private List<ShopImportDTO> shopImportDTOS;

    public ShopImportWrapperDTO() {}

    public ShopImportWrapperDTO(List<ShopImportDTO> laptopImportDTOList) {
        this.shopImportDTOS = laptopImportDTOList;
    }

    public List<ShopImportDTO> getShopImportDTOS() {
        return shopImportDTOS;
    }

    public void setShopImportDTOS(List<ShopImportDTO> shopImportDTOS) {
        this.shopImportDTOS = shopImportDTOS;
    }
}
