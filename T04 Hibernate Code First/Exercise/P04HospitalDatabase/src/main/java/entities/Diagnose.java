package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnoses")
public class Diagnose {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    public Diagnose() {}

    public Diagnose(String name, String comments, Patient patient) {
        this.name = name;
        this.comments = comments;
        this.patient = patient;
    }
}
