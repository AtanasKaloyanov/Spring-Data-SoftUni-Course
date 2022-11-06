package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "visitations")
@Entity
public class Visitation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="date" )
    private LocalDate date;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    public Visitation() {}

    public Visitation(LocalDate date, String comments, Patient patient) {
        this.date = date;
        this.comments = comments;
        this.patient = patient;
    }
}
