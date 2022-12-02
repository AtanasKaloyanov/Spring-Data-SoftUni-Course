package exam.model.dto.shop;

import exam.model.dto.town.Town;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopImportDTO {

    @Size(min = 4)
    @NotNull
    @XmlElement
    private String name;

    @Min(20000)
    @NotNull
    @XmlElement
    private Double income;

    @Size(min = 4)
    @NotNull
    @XmlElement
    private String address;

    @Min(1)
    @Max(50)
    @NotNull
    @XmlElement(name = "employee-count")
    private Integer employeeCount;

    @Min(50)
    @NotNull
    @XmlElement(name = "shop-area")
    private Integer shopArea;

    @XmlElement(name = "town")
    private Town townWrapperDTO;

    public ShopImportDTO() {}

    public ShopImportDTO(String name, Double income, String address, Integer employeeCount, Integer shopArea, Town townWrapperDTO) {
        this.name = name;
        this.income = income;
        this.address = address;
        this.employeeCount = employeeCount;
        this.shopArea = shopArea;
        this.townWrapperDTO = townWrapperDTO;
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

    public Town getTownWrapperDTO() {
        return townWrapperDTO;
    }

    public void setTownWrapperDTO(Town townWrapperDTO) {
        this.townWrapperDTO = townWrapperDTO;
    }
}
