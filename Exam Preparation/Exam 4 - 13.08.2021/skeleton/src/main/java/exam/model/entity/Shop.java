package exam.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shops")
public class Shop extends BaseEntity {

    @Size(min = 4)
    @Column(unique = true, nullable = false)
    private String name;

    @Min(20000)
    @Column(nullable = false)
    private Double income;

    @Size(min = 4)
    @Column(nullable = false)
    private String address;

    @Min(1)
    @Max(50)
    @Column(name = "employee_count", nullable = false)
    private Integer employeeCount;

    @Min(50)
    @Column
    private Integer shopArea;

    @ManyToOne
    private Town town;

    public Shop() {}

    public Shop(String name, Double income, String address, Integer employeeCount, Integer shopArea, Town town) {
        this.name = name;
        this.income = income;
        this.address = address;
        this.employeeCount = employeeCount;
        this.shopArea = shopArea;
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public Integer getShopArea() {
        return shopArea;
    }

    public void setShopArea(Integer shopArea) {
        this.shopArea = shopArea;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
