package com.example.football.models.dto.player;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerWrapperDTO {

    @XmlElement(name = "player")
    private List<PlayerImportDTO> playerImportDTOList;

    public PlayerWrapperDTO() {}

    public PlayerWrapperDTO(List<PlayerImportDTO> playerImportDTOList) {
        this.playerImportDTOList = playerImportDTOList;
    }

    public List<PlayerImportDTO> getPlayerImportDTOList() {
        return playerImportDTOList;
    }

    public void setPlayerImportDTOList(List<PlayerImportDTO> playerImportDTOList) {
        this.playerImportDTOList = playerImportDTOList;
    }
}
