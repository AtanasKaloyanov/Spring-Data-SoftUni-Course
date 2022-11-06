import entities.Course;
import entities.Student;
import entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "soft-uni";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Teacher teacher = new Teacher("Pesho", "Peshev", "088", "@", 50.00);
        Student student = new Student("Atanas", "Kaloyanov", "088", 6.00, 100);
        Course course = new Course("OOP", "important", new Date(), new Date(), 100.00, teacher);

        student.getCourses().add(course);

        entityManager.persist(teacher);
        entityManager.persist(student);
        entityManager.persist(course);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
