package problems;

import common.PrintPatterns;
import common.Queries;
import common.Utils;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class P10IncreaseSalaries {

    public static void main(String[] args) {

        final EntityManager entityManager = Utils.beginTransaction();

        entityManager.createQuery(Queries.P10_INCREASE_SALARY_WITH_12_PERCENT).executeUpdate();

        final List<Employee> employeeList = entityManager.createQuery(Queries.P10_SELECT_ALL_EMPLOYEES_WITH_INCREASED_SALARY, Employee.class).getResultList();

        employeeList.forEach(employee -> System.out.printf(PrintPatterns.P10_PRINT_PATTERN,
                employee.getFirstName(), employee.getLastName(), employee.getSalary()));

        Utils.endTransaction(entityManager);
    }
}
