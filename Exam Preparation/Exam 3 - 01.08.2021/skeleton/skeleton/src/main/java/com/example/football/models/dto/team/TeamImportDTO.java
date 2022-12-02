package com.example.football.models.dto.team;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TeamImportDTO {
    @Size(min = 3)
    @NotNull
    private String name;

    @Size(min = 3)
    @NotNull
    private String stadiumName;

    @Min(1000)
    @NotNull
    private Integer fanBase;

    @Size(min = 10)
    @NotNull
    private String history;

    @NotNull
    private String townName;

    public TeamImportDTO() {}

    public TeamImportDTO(String name, String stadiumName, Integer fanBase, String history, String townName) {
        this.name = name;
        this.stadiumName = stadiumName;
        this.fanBase = fanBase;
        this.history = history;
        this.townName = townName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Integer getFanBase() {
        return fanBase;
    }

    public void setFanBase(Integer fanBase) {
        this.fanBase = fanBase;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
