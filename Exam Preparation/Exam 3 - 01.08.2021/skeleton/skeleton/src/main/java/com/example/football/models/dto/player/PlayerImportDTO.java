package com.example.football.models.dto.player;

import com.example.football.models.dto.stat.StatWrapperDTO;
import com.example.football.models.dto.team.TeamWrapperDTO;
import com.example.football.models.dto.town.TownWrapperDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerImportDTO {

    @XmlElement(name = "first-name")
    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    @XmlElement(name = "last-name")
    private String lastName;

    @XmlElement
    @Email
    private String email;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement
    private String position;

    @XmlElement
    private TownWrapperDTO town;

    @XmlElement
    private TeamWrapperDTO team;

    @XmlElement
    private StatWrapperDTO stat;

    public PlayerImportDTO() {}

    public PlayerImportDTO(String firstName, String lastName, String email, String birthDate, String position, TownWrapperDTO town, TeamWrapperDTO team, StatWrapperDTO stat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.position = position;
        this.town = town;
        this.team = team;
        this.stat = stat;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public TownWrapperDTO getTown() {
        return town;
    }

    public void setTown(TownWrapperDTO town) {
        this.town = town;
    }

    public TeamWrapperDTO getTeam() {
        return team;
    }

    public void setTeam(TeamWrapperDTO team) {
        this.team = team;
    }

    public StatWrapperDTO getStat() {
        return stat;
    }

    public void setStat(StatWrapperDTO stat) {
        this.stat = stat;
    }
}
