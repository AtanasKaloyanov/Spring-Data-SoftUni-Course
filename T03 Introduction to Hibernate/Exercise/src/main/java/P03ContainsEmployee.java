import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P03ContainsEmployee {
    private static final String DATABASE_NAME = "soft_uni";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        String inputFirstName = input[0];
        String inputLastName = input[1];

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Long numberMatches = entityManager.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln", Long.class)
                .setParameter("fn", inputFirstName)
                .setParameter("ln", inputLastName)
                .getSingleResult();

        if (numberMatches == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
