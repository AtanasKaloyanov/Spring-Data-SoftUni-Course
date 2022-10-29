import entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school-db");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

     Student found = entityManager.find(Student.class,  3);
entityManager.remove(found);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
