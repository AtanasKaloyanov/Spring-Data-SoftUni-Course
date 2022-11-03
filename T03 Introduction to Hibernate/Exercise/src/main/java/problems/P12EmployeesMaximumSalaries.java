package problems;

import common.Constants;
import common.PrintPatterns;
import common.Queries;
import common.Utils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class P12EmployeesMaximumSalaries {

    public static void main(String[] args) {

        final EntityManager entityManager = Utils.beginTransaction();

        final List<Object[]> objectList = entityManager
                .createQuery(Queries.P12_GET_MAX_SALARY_FOR_EVERY_DEPARTMENT, Object[].class)
                .setParameter(Constants.P12_VARIABLE_NAME_FOR_SETTING, BigDecimal.valueOf(Constants.P12_FIRST_BORDER_30000))
                .setParameter(Constants.P12_VARIABLE_NAME_FOR_SETTING2, BigDecimal.valueOf(Constants.P12_SECOND_BORDER_70000))
                .getResultList();

        objectList.forEach(pair -> System.out.printf(PrintPatterns.P12_PRINT_PATTERN, pair[0], pair[1]));

        Utils.endTransaction(entityManager);
    }
}
