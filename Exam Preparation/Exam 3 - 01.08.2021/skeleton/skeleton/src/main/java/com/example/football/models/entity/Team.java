package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    @Size(min = 3)
    @Column(nullable = false)
    private String name;

    @Size(min = 3)
    @Column(name = "stadium_name", nullable = false)
    private String stadiumName;

    @Min(1000)
    @Column(name = "fan_base", nullable = false)
    private Integer fanBase;

    @Size(min = 10)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String history;

    @ManyToOne()
    private Town townName;

    public Team() {
    }

    public Team(String name, String stadiumName, Integer fanBase, String history, Town town) {
        this.name = name;
        this.stadiumName = stadiumName;
        this.fanBase = fanBase;
        this.history = history;
        this.townName = town;
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

    public Town getTownName() {
        return townName;
    }

    public void setTownName(Town townName) {
        this.townName = townName;
    }
}
