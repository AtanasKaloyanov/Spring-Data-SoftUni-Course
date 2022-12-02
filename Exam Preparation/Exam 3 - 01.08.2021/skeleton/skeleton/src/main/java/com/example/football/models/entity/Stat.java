package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "stats")
public class Stat extends BaseEntity {
    @Positive
    @Column(nullable = false)
    private Float shooting;

    @Positive
    @Column(nullable = false)
    private Float passing;

    @Positive
    @Column(nullable = false)
    private Float endurance;

    public Stat() {}

    public Stat(Float shooting, Float passing, Float endurance) {
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
