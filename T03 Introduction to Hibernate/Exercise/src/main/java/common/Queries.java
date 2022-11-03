package common;

public enum Queries {
    ;
    public static final String P02_SELECT_ALL_TOWNS = "From Town";

    public static final String P03_SELECT_EMPLOYEES_COUNT_BY_GIVEN_NAMES = "SELECT COUNT(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln";

    public static final String P04_GET_EMPOYEES_NAMES_WITH_SALARY_OVER_50000 = "SELECT e.firstName FROM Employee e WHERE e.salary > 50000";

    public static final String P05_GET_EMPLOYEES_FROM_RESEARCH_AND_DEVELOPMENT_DEPARTMENT = "SELECT e FROM Employee e WHERE e.department.name = :dep ORDER BY e.salary, e.id";

    public static final String P06_SET_EMPLOYEE_ADDRESS_TEXT_BY_GIVEN_LAST_NAME = "UPDATE Employee e SET e.address = :add WHERE e.lastName = :ln";

    public static final String P07_GET_ALL_ADDRESSES_BY_NUMBER_OF_EMPLOYEES = "SELECT a FROM Address a ORDER BY a.employees.size DESC";

    public static final String P08_GET_EMPLOYEE_BY_ID = "SELECT e FROM Employee e WHERE e.id = :ID";

    public static final String P09_GET_LATEST_10_PROJECTS_BY_START_DATE_DESC = "SELECT p FROM Project p ORDER BY p.startDate DESC";

    public static final String P10_INCREASE_SALARY_WITH_12_PERCENT = "UPDATE Employee e SET salary = salary * 1.12 WHERE e.department.id IN(1, 2, 4, 11)";
    public static final String P10_SELECT_ALL_EMPLOYEES_WITH_INCREASED_SALARY = "SELECT e FROM Employee e WHERE  e.department.id IN(1, 2, 4, 11)";

    public static final String P11_GET_ALL_EMPLOYEES_BY_GIVEN_PATTERN = "SELECT e FROM Employee e WHERE firstName LIKE :givenPattern";

    public static final String P12_GET_MAX_SALARY_FOR_EVERY_DEPARTMENT =
            "SELECT e.department.name, MAX(e.salary) " +
                    "FROM Employee e " +
                    "GROUP BY e.department.name " +
                    "HAVING MAX(e.salary) NOT BETWEEN :border AND :second_border";

    public static final String P13_SELECT_ADDRESSES_BY_GIVEN_TOWN = "SELECT a FROM Address a WHERE a.town.name = :town";
    public static final String P13_GET_TOWN_BY_NAME = "SELECT t FROM Town t WHERE t.name = :town";
}
