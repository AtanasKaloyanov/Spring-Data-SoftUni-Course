import entities.BankAccount;
import entities.CreditCard;
import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "soft-uni";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        User user = new User("Atanas", "Kaloyanov", "@", "guess");
        CreditCard creditCard = new CreditCard(10, user, "normal", 10, 2020);
        BankAccount bankAccount = new BankAccount(11, user, "SoftUniBank", "abc123");

        user.getBillingDetail().add(creditCard);
        user.getBillingDetail().add(bankAccount);

        entityManager.persist(user);
        entityManager.persist(creditCard);
        entityManager.persist(bankAccount);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
