package com.example.football.models.dto.town;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownImportDTO {

    @Size(min = 2)
    @NotNull
    private String name;

    @Positive
    @NotNull
    private Integer population;

    @Size(min = 10)
    @NotNull
    private String travelGuide;

    public TownImportDTO() {
    }

    public TownImportDTO(String name, Integer population, String travelGuide) {
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
