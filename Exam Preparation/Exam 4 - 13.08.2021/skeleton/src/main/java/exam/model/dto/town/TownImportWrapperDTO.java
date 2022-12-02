package exam.model.dto.town;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownImportWrapperDTO {

    @XmlElement(name = "town")
    private List<TownImportDTO> townImportDTOList;

    public TownImportWrapperDTO() {}

    public TownImportWrapperDTO(List<TownImportDTO> townImportDTOList) {
        this.townImportDTOList = townImportDTOList;
    }

    public List<TownImportDTO> getTownImportDTOList() {
        return townImportDTOList;
    }

    public void setTownImportDTOList(List<TownImportDTO> townImportDTOList) {
        this.townImportDTOList = townImportDTOList;
    }
}
