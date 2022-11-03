package problems;

import common.Constants;
import common.PrintPatterns;
import common.Utils;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class P03ContainsEmployee {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        final String[] input = scanner.nextLine().split(" ");
        final String inputFirstName = input[0];
        final String inputLastName = input[1];

        final EntityManager entityManager = Utils.beginTransaction();

        final Long numberMatches = entityManager.createQuery(common.Queries.P03_SELECT_EMPLOYEES_COUNT_BY_GIVEN_NAMES, Long.class)
                .setParameter(Constants.P03_FIRST_VARIABLE_NAME_FOR_SETTING, inputFirstName)
                .setParameter(Constants.P03_SECOND_VARIABLE_NAME_FOR_SETTING, inputLastName)
                .getSingleResult();

        if (numberMatches == 0) {
            System.out.println(PrintPatterns.P03_NO);
        } else {
            System.out.println(PrintPatterns.P03_YES);
        }

        Utils.endTransaction(entityManager);
    }
}
