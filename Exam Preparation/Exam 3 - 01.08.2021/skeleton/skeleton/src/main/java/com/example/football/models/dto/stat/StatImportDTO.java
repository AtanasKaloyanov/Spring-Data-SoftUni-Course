package com.example.football.models.dto.stat;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatImportDTO {

    @Positive
    @NotNull
    @XmlElement
    private Float shooting;

    @Positive
    @NotNull
    @XmlElement
    private Float passing;

    @Positive
    @NotNull
    @XmlElement
    private Float endurance;

    public StatImportDTO() {}

    public StatImportDTO(Float shooting, Float passing, Float endurance) {
        this.shooting = shooting;
        this.passing = passing;
        this.endurance = endurance;
    }

    public Float getShooting() {
        return shooting;
    }

    public void setShooting(Float shooting) {
        this.shooting = shooting;
    }

    public Float getPassing() {
        return passing;
    }

    public void setPassing(Float passing) {
        this.passing = passing;
    }

    public Float getEndurance() {
        return endurance;
    }

    public void setEndurance(Float endurance) {
        this.endurance = endurance;
    }
}
