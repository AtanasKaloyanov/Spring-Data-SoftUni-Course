package problems;

import common.Utils;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class P02ChangeCasing {

    public static void main(String[] args) {

        final EntityManager entityManager = Utils.beginTransaction();

        final Query getAllTowns = entityManager.createQuery(common.Queries.P02_SELECT_ALL_TOWNS, Town.class);
        final List<Town> allTowns = getAllTowns.getResultList();

        allTowns.stream()
                .filter(town -> town.getName().length() <= common.Constants.P02_FIVE)
                .forEach(town -> {
                    town.setName(town.getName().toUpperCase());
                    entityManager.persist(town);
                });

        Utils.endTransaction(entityManager);
    }
}
