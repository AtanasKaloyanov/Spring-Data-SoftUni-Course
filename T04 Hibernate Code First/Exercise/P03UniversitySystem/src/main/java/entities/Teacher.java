package entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")

public class Teacher extends Person{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String email;

    @Column(name = "salary_per_hour")
    private Double salaryPerHour;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;

    public Teacher() {

    }

    public Teacher(String firstName, String lastName, String phoneNumber, String email, Double salaryPerHour) {
        super(firstName, lastName, phoneNumber);
        this.courses = new HashSet<>();
        this.email = email;
        this.salaryPerHour = salaryPerHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(Double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
