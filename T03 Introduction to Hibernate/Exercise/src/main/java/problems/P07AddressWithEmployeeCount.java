package problems;

import common.Constants;
import common.Queries;
import common.Utils;
import entities.Address;

import javax.persistence.EntityManager;
import java.util.List;

public class P07AddressWithEmployeeCount {

    public static void main(String[] args) {

        final EntityManager entityManager = Utils.beginTransaction();

        final List<Address> addressList = entityManager.createQuery(Queries.P07_GET_ALL_ADDRESSES_BY_NUMBER_OF_EMPLOYEES, Address.class).setMaxResults(Constants.P07_TEN).getResultList();

        addressList.forEach(System.out::println);

        Utils.endTransaction(entityManager);
    }
}
