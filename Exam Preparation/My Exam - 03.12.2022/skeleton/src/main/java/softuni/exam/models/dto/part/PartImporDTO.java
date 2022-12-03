package softuni.exam.models.dto.part;
import javax.validation.constraints.*;

public class PartImporDTO {
    @Size(min = 2, max = 19)
    @NotNull
    private String partName;


    @Min(10)
    @Max(2000)
    @NotNull
    private Double price;

    @Positive
    @NotNull
    private Integer quantity;

    public PartImporDTO() {}

    public PartImporDTO(String partName, Double price, Integer quantity) {
        this.partName = partName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
