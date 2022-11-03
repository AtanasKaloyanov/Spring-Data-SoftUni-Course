package problems;

import common.Constants;
import common.PrintPatterns;
import common.Utils;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class P05EmployeesFromDepartments {

    public static void main(String[] args) {

        final EntityManager entityManager = Utils.beginTransaction();

        final List<Employee> employeesList = entityManager.createQuery(common.Queries.P05_GET_EMPLOYEES_FROM_RESEARCH_AND_DEVELOPMENT_DEPARTMENT, Employee.class)
                .setParameter(Constants.P05_VARIABLE_FOR_SETTING, Constants.P05_DEPARTMENT_NAME)
                .getResultList();

        employeesList.forEach(employee -> System.out.printf(PrintPatterns.P05_PRINT_PATTERN, employee.getFirstName(),
                employee.getLastName(), employee.getSalary()));

        Utils.endTransaction(entityManager);
    }
}
