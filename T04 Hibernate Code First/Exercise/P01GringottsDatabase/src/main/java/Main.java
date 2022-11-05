import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "soft_uni";
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        LocalDateTime depositStartDate = LocalDateTime.of(2019, 3, 28, 14, 33, 48, 0);
        LocalDateTime depositExpirationDate = LocalDateTime.of(2022, 3, 28, 14, 33, 48, 0);

        WizardDeposit wizardDeposit = new WizardDeposit("Atanas", "Kaloyanov", "Guess", 1001, "Harry", 100, "SoftUni",
                depositStartDate, 200.00, 3.00, 10.20, depositExpirationDate, true);

        entityManager.persist(wizardDeposit);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
