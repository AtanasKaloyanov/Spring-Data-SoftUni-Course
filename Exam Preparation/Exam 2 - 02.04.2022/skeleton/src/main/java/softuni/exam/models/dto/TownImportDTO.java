package softuni.exam.models.dto;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class TownImportDTO {

    @Size(min = 2)
    private String townName;

    @Min(1)
    private Long population;

    public TownImportDTO() {}

    public TownImportDTO(String townName, Long population) {
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
