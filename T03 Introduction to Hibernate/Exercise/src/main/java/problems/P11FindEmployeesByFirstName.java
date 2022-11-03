package problems;

import common.Constants;
import common.PrintPatterns;
import common.Queries;
import common.Utils;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class P11FindEmployeesByFirstName {

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.beginTransaction();

        final Scanner scanner = new Scanner(System.in);
        final String pattern = scanner.nextLine();

        final List<Employee> employeeList = entityManager.createQuery(Queries.P11_GET_ALL_EMPLOYEES_BY_GIVEN_PATTERN, Employee.class)
                .setParameter(Constants.P11_VARIABLE_NAME_FOR_SETTING, pattern + Constants.P11_PERCENT)
                .getResultList();

        employeeList.forEach(employee -> System.out.printf(PrintPatterns.P11_PRINT_PATTERN,
                employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), employee.getSalary()));

        Utils.endTransaction(entityManager);
    }
}
