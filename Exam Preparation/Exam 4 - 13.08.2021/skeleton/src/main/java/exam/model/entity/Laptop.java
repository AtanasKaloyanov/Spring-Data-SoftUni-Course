package exam.model.entity;

import exam.model.entity.enums.WarrantyType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "laptops")
public class Laptop extends BaseEntity{

    @Size(min = 8)
    @Column(name = "mac_address", nullable = false)
    private String macAddress;

    @Positive
    @Column(nullable = false)
    private Double cruSpeed;

    @Min(8)
    @Max(128)
    @Column(nullable = false)
    private Integer ram;

    @Min(128)
    @Max(1024)
    @Column(nullable = false)
    private Integer storage;

    @Size(min = 10)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Positive
    @Column(nullable = false)
    private Double price;

    @Column(name = "warranty_type", nullable = false)
    private WarrantyType warrantyType;

    @ManyToOne
    private Shop shop;

    public Laptop() {}

    public Laptop(String macAddress, Double cruSpeed, Integer ram, Integer storage, String description, Double price, WarrantyType warrantyType, Shop shop) {
        this.macAddress = macAddress;
        this.cruSpeed = cruSpeed;
        this.ram = ram;
        this.storage = storage;
        this.description = description;
        this.price = price;
        this.warrantyType = warrantyType;
        this.shop = shop;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Double getCruSpeed() {
        return cruSpeed;
    }

    public void setCruSpeed(Double cruSpeed) {
        this.cruSpeed = cruSpeed;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
