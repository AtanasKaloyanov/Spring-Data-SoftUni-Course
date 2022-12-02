package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "agents")
public class Agent extends BaseEntity{

    @Size(min = 2)
    @Column(name = "first_name", unique = true)
    private String firstName;

    @Size(min = 2)
    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    @Email
    private String email;

    @ManyToOne()
    private Town town;

    public Agent() {}

    public Agent(String firstName, String lastName, String email, Town town) {
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

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
