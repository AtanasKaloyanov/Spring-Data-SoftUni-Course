package problems;

import common.Constants;
import common.PrintPatterns;
import common.Queries;
import common.Utils;
import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class P13RemoveTowns {

    public static void main(String[] args) {

        final EntityManager entityManager = Utils.beginTransaction();

        final Scanner scanner = new Scanner(System.in);
        final String givenTown = scanner.nextLine();

        final List<Address> addressesList = entityManager.createQuery(common.Queries.P13_SELECT_ADDRESSES_BY_GIVEN_TOWN, Address.class)
                .setParameter(Constants.P13_VARIABLE_NAME_FOR_SETTIG, givenTown)
                .getResultList();

        final long numberAddressesForDeleting = addressesList.size();

        if (numberAddressesForDeleting == 0) {
            System.out.println(PrintPatterns.P13_PRINT_MESSAGE_BY_NONEXISTENT_TOWN);
            entityManager.close();
            return;
        }

        addressesList.forEach(address -> {
            address.getEmployees().forEach(employee -> employee.setAddress(null));
            entityManager.remove(address);
        });

        Town townForDeleting = entityManager.createQuery(Queries.P13_GET_TOWN_BY_NAME, Town.class)
                .setParameter(Constants.P13_VARIABLE_NAME_FOR_SETTIG, givenTown)
                .getSingleResult();

        entityManager.remove(townForDeleting);

        String address = numberAddressesForDeleting == 1 ? Constants.P13_ADDRESS : Constants.P13_ADDRESSES;
        System.out.printf(PrintPatterns.P13_PRINT_PATTERN, numberAddressesForDeleting, address, givenTown);

        Utils.endTransaction(entityManager);
    }
}
