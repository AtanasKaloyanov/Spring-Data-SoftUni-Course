package softuni.exam.models.dto.agent;

import softuni.exam.models.entity.Town;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AgentImportDTO {
    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    private String lastName;

    private String email;

    private String town;

    public AgentImportDTO() {}

    public AgentImportDTO(String firstName, String lastName, String email, String town) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
