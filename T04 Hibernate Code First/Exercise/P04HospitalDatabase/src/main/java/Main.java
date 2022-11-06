import entities.Diagnose;
import entities.Medicament;
import entities.Patient;
import entities.Visitation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
        private static final String PERSISTENCE_UNIT_NAME = "soft-uni";

        public static void main(String[] args) {

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Patient patient = new Patient("Atanas", "Kaloyanov", "out of space", "@", true, LocalDate.now());

            Diagnose diagnose = new Diagnose("developer", "Coding every day", patient);
            Visitation visitation = new Visitation(LocalDate.of(2022, 11, 6), "coding hospital visitation" ,patient);

            Medicament medicament = new Medicament("Java");
            medicament.getPatients().add(patient);

            entityManager.persist(patient);
            entityManager.persist(diagnose);
            entityManager.persist(medicament);
            entityManager.persist(visitation);

            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

