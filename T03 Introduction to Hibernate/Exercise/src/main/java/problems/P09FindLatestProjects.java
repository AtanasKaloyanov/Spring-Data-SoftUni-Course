package problems;

import common.Constants;
import common.Queries;
import common.Utils;
import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

public class P09FindLatestProjects {

    public static void main(String[] args) {

        final EntityManager entityManager = Utils.beginTransaction();

        final List<Project> projectList = entityManager.createQuery(Queries.P09_GET_LATEST_10_PROJECTS_BY_START_DATE_DESC, Project.class)
                .setMaxResults(Constants.P09_FIRST_10_RESULTS)
                .getResultList();

        projectList.stream().sorted(Comparator.comparing(Project::getName))
                .forEach(System.out::println);

        Utils.endTransaction(entityManager);
    }
}