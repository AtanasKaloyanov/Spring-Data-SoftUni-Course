package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {


    @Size(min = 2)
    @Column(name = "town_name", unique = true)
    private String townName;

    @Min(1)
    @Column
    private Long population;

    public Town() {}

    public Town(String townName, Long population) {
        this.townName = townName;
        this.population = population;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}
