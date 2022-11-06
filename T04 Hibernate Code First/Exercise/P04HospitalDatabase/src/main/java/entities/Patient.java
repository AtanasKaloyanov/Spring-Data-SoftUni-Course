package entities;

import jakarta.persistence.*;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "patients")
@Entity
public class Patient {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private Blob picture;

    @Column(name = "has_insurance")
    private boolean hasInsurance;

    @OneToMany(mappedBy = "patient")
    private Set<Visitation> visitations;

    @OneToMany(mappedBy = "patient")
    private Set<Diagnose> diagnoses;

    @ManyToMany(mappedBy = "patients")
    private Set<Medicament> medicaments;

    public Patient() {
        this.visitations = new HashSet<>();
        this.diagnoses = new HashSet<>();
        this.medicaments = new HashSet<>();
    }

    public Patient(String firstName, String lastName, String address, String email, boolean hasInsurance, LocalDate bithtDay) {
        this();

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.hasInsurance = hasInsurance;
        this.birthDate = bithtDay;
    }
}
