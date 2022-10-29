

import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class HibernateMain {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();

        SessionFactory sessionFactory = cfg.buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

//      Student example = new Student();
//      example.setName("Tosho");
//        session.persist(example);

         Student fromDB = session.get(Student.class, 1L);
      //  System.out.println(fromDB.getId() + " " + fromDB.getName());
        // code

        List<Student> students = session.createQuery("FROM Student AS `s` WHERE s.name = 'Gosho'", Student.class).list();

        for (int i = 0; i < students.size(); i++) {
           System.out.println(students.get(i).getId() + " " +  students.get(i).getName());
        }

        session.getTransaction().commit();
        session.close();
    }
}
