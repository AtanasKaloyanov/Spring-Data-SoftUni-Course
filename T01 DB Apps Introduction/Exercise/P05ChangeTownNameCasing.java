import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P05ChangeTownNameCasing {
    private static final String SELECT_TOWN_NAMES_BY_COUNTRY = """
            SELECT `name`\s
            FROM `towns`
            WHERE `country` = ?;""";

    private static final String SET_UPPER_NAMES_BY_COUNTRY = """
            UPDATE `towns`\s
            SET `name` = UPPER(`name`)
            WHERE `country` = ?;\s
            """;

    private static final String MESSAGE_BY_0_AFFECTED_TOWNS = "No town names were affected.";
    private static final String MESSAGE_BY_ONE_TOWN = "1 town name was affected.";
    private static final String MESSAGE_BY_MORE_THAN_ONE_TOWN = "%d town names were affected.%n";
    private static final String TOWN_COLUMN_NAME = "name";

    public static void main(String[] args) throws SQLException {
        Connection connection = P01GetConnection.getConnection();

        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();

        int affectedNames = getAffectedNames(connection, country);

        if (affectedNames == 0) {
            System.out.println(MESSAGE_BY_0_AFFECTED_TOWNS);
            return;
        }

        ResultSet selectNamesResult = getResultSet(connection, country);
        List<String> names = new ArrayList<>();

        while (selectNamesResult.next()) {
            names.add(selectNamesResult.getString(TOWN_COLUMN_NAME));
        }

        if (names.size() == 1) {
            System.out.println(MESSAGE_BY_ONE_TOWN);
        } else {
            System.out.printf(MESSAGE_BY_MORE_THAN_ONE_TOWN, names.size());
        }

        System.out.println(names);

        connection.close();
    }

    private static ResultSet getResultSet(Connection connection, String country) throws SQLException {
        PreparedStatement selectNamesStatement = connection.prepareStatement(SELECT_TOWN_NAMES_BY_COUNTRY);
        selectNamesStatement.setString(1, country);
        return selectNamesStatement.executeQuery();
    }

    private static int getAffectedNames(Connection connection, String country) throws SQLException {
        PreparedStatement setNamesStatement = connection.prepareStatement(SET_UPPER_NAMES_BY_COUNTRY);
        setNamesStatement.setString(1, country);
        return setNamesStatement.executeUpdate();
    }
}
