import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class P02ChangeCasing {
    private static final String DATABASE_NAME = "soft_uni";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query getAllTowns = entityManager.createQuery("From Town", Town.class);
        List<Town> allTowns = getAllTowns.getResultList();

        allTowns.stream()
                .filter(town -> town.getName().length() <= 5)
                        .forEach(town -> {
                            town.setName(town.getName().toUpperCase());
                            entityManager.persist(town);
                        });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
