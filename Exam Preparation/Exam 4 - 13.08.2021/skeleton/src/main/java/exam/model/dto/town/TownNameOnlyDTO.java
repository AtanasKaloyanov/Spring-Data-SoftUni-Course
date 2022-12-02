package exam.model.dto.town;

public class TownNameOnlyDTO {
    private String name;

    public TownNameOnlyDTO() {}

    public TownNameOnlyDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
