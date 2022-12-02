package exam.model.dto.customer;

import exam.model.dto.town.Town;
import exam.model.dto.town.TownNameOnlyDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerImportDTO {
    @Size(min = 2)
    @NotNull
    private String firstName;

    @Size(min = 2)
    @NotNull
    private String lastName;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String registeredOn;

    private TownNameOnlyDTO town;

    public CustomerImportDTO() {}

    public CustomerImportDTO(String firstName, String lastName, String email, String registeredOn, TownNameOnlyDTO town) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registeredOn = registeredOn;
        this.town = town;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }

    public TownNameOnlyDTO getTown() {
        return town;
    }

    public void setTown(TownNameOnlyDTO town) {
        this.town = town;
    }
}
