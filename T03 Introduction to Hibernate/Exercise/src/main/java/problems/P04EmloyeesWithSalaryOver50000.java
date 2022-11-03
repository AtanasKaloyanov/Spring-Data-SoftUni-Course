package problems;

import common.Utils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class P04EmloyeesWithSalaryOver50000 {

    public static void main(String[] args) {

        final EntityManager entityManager = Utils.beginTransaction();

        final Query getEmployeesWithBigSalary = entityManager.createQuery(common.Queries.P04_GET_EMPOYEES_NAMES_WITH_SALARY_OVER_50000);
        final List<String> getEmployeesWithBigSalaryList = getEmployeesWithBigSalary.getResultList();

        getEmployeesWithBigSalaryList.forEach(System.out::println);

        Utils.endTransaction(entityManager);
    }
}
