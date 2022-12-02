package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    @Size(min = 2, max = 60)
    @Column(name = "city_name", unique = true, nullable = false)
    private String cityName;

    @Size(min = 2)
    @Column(columnDefinition = "TEXT")
    private String description;

    @Min(500)
    @Column(nullable = false)
    private Long population;

    @ManyToOne()
    private Country country;

    public City() {}

    public City(String cityName, String description, Long population, Country country) {
        this.cityName = cityName;
        this.description = description;
        this.population = population;
        this.country = country;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
