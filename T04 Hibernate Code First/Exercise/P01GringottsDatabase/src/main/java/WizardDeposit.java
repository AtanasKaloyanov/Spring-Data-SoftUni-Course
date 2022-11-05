import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wizard_deposits")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class WizardDeposit {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(nullable = false)
    private int age;

    @Column(name = "magic_wand_creator", length = 100)
    private String magigWandCreator;

    @Column(name = "magic_wand_size")
    private int magicWandSize;

    @Column(name = "deposit_group", length = 20)
    private String depositGroup;

    @Column(name = "deposit_start_date")
    private LocalDateTime depositStartDate;

    @Column(name = "deposit_amount")
    private double depositAmount;

    @Column(name = "deposit_interest")
    private double depositInterest;

    @Column(name = "deposit_charge")
    private double depositCharge;

    @Column(name = "deposit_expiration_date")
    private LocalDateTime depositExpirationDate;

    @Column(name = "is_deposit_expired")
    private boolean isDepositExpired;

    public WizardDeposit() {

    }

    public WizardDeposit(String firstName, String lastName, String notes, int age,
                         String magigWandCreator, int magicWandSize, String depositGroup,
                         LocalDateTime depositStartDate, double depositAmount, double depositInterest,
                         double depositCharge, LocalDateTime depositExpirationDate, boolean isDepositExpired) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
        this.age = age;
        this.magigWandCreator = magigWandCreator;
        this.magicWandSize = magicWandSize;
        this.depositGroup = depositGroup;
        this.depositStartDate = depositStartDate;
        this.depositAmount = depositAmount;
        this.depositInterest = depositInterest;
        this.depositCharge = depositCharge;
        this.depositExpirationDate = depositExpirationDate;
        this.isDepositExpired = isDepositExpired;
    }
}
