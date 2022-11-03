package problems;

import common.Constants;
import common.Queries;
import common.Utils;
import entities.Address;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class P06AddingANewAddressAndUpdatingEmployee {

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.beginTransaction();

        final Scanner scanner = new Scanner(System.in);
        final String givenLastName = scanner.nextLine();

        Address changedAddress = new Address();
        changedAddress.setText(common.Constants.P06_GIVEN_ADDRESS_TEXT);
        entityManager.persist(changedAddress);

        entityManager.createQuery(Queries.P06_SET_EMPLOYEE_ADDRESS_TEXT_BY_GIVEN_LAST_NAME)
                .setParameter(Constants.P06_FIRST_VARIABLE_NAME_FOR_SETTING, changedAddress)
                .setParameter(Constants.P06_SECOND_VARIABLE_NAME_FOR_SETTING, givenLastName)
                .executeUpdate();

        Utils.endTransaction(entityManager);
    }
}
