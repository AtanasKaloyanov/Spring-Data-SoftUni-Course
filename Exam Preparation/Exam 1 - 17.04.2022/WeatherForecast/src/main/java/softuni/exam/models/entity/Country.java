package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    @Size(min = 2, max = 60)
    @Column(name = "country_name", unique = true, nullable = false)
    private String countryName;

    @Size(min = 2, max = 20)
    @Column(nullable = false)
    private String currency;

    public Country() {}

    public Country(String countryName, String currency) {
        this.countryName = countryName;
        this.currency = currency;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
