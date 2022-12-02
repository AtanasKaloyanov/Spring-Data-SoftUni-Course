package softuni.exam.models.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityImportDTO {

    @Size(min = 2, max = 60)
    @NotNull
    private String cityName;
    @Size(min = 2)
    private String description;

    @Min(500)
    @NotNull
    private Long population;
    private Long country;


    public CityImportDTO() {}

    public CityImportDTO(String cityName, String description, Long population, Long country) {
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

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
