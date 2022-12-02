package softuni.exam.models.dto;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryImportDTO {

    @Size(min = 2, max = 60)
    private String countryName;

    @Size(min = 2, max = 20)
    private String currency;

    public CountryImportDTO() {}

    public CountryImportDTO(String countryName, String currency) {
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
