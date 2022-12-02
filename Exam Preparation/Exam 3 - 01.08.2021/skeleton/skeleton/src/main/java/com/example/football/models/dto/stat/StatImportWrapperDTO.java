package com.example.football.models.dto.stat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatImportWrapperDTO {

    @XmlElement(name = "stat")
    private List<StatImportDTO> statImportDTOList;

    public StatImportWrapperDTO() {}

    public List<StatImportDTO> getStatImportDTOList() {
        return statImportDTOList;
    }

    public void setStatImportDTOList(List<StatImportDTO> statImportDTOList) {
        this.statImportDTOList = statImportDTOList;
    }
}
