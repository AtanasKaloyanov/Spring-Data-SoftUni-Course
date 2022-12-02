package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    @Size(min = 2)
    @Column(unique = true, nullable = false)
    private String name;

    @Positive
    @Column(nullable = false)
    private Integer population;

    @Size(min = 10)
    @Column(name = "travel_guide", columnDefinition = "TEXT", nullable = false)
    private String travelGuide;

    public Town() {}

    public Town(String name, Integer population, String travelGuide) {
        this.name = name;
        this.population = population;
        this.travelGuide = travelGuide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
