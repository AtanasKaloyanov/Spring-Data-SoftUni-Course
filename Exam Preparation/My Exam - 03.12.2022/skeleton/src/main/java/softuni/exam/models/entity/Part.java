package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "parts")
public class Part extends BaseEntity{

    @Size(min = 2, max = 19)
    @Column(name = "part_name", unique = true, nullable = false)
    private String partName;


    @Min(10)
    @Max(2000)
    @Column(nullable = false)
    private Double price;


    @Positive
    @Column(nullable = false)
    private Integer quantity;

    public Part() {}

    public Part(String partName, Double price, Integer quantity) {
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
