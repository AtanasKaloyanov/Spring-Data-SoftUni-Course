package problems;

import common.Constants;
import common.PrintPatterns;
import common.Queries;
import common.Utils;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.Scanner;

public class P08GetEmployeesWithProject {

    public static void main(String[] args) {

        final EntityManager entityManager = Utils.beginTransaction();

        final Scanner scanner = new Scanner(System.in);
        final int givenId = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager.createQuery(Queries.P08_GET_EMPLOYEE_BY_ID, Employee.class)
                .setParameter(Constants.P08_VARIABLE_NAME_FOR_SETTING, givenId)
                .getSingleResult();

        System.out.printf(PrintPatterns.P08_FIRST_PRINT_PATTERN, employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> System.out.printf(PrintPatterns.P08_SECOND_PRINT_PATTERN, project.getName()));

        Utils.endTransaction(entityManager);
    }
}
