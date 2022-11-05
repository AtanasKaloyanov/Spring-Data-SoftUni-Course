import entities.Customer;
import entities.Product;
import entities.Sale;
import entities.StoreLocation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "soft-uni";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Customer customer = new Customer("Atanas", "@", "123$");
        Product product = new Product("Computer", 2, BigDecimal.valueOf(1000));
        StoreLocation storeLocation = new StoreLocation("Sofia");

        Sale sale = new Sale(product, customer, storeLocation, new Date());

        entityManager.persist(customer);
        entityManager.persist(product);
        entityManager.persist(storeLocation);
        entityManager.persist(sale);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
