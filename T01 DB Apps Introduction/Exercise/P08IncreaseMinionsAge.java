import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P08IncreaseMinionsAge {
    private static final String SELECT_MINION_ID_NAME_AND_AGE =
            "SELECT `id`, `name`, `age` \n" +
                    "FROM `minions`;";

    private static final String SET_MINION_NAME_AND_AGE = """
            UPDATE `minions`
            SET `name` = LOWER(`name`), `age` = `age` + 1
            WHERE `id` IN (%s);""";

    private static final String MINION_NAME_COLUMN = "name";
    private static final String MINION_AGE_COLUMN = "age";


    public static void main(String[] args) throws SQLException {
        Connection connection = P01GetConnection.getConnection();
        Scanner scanner = new Scanner(System.in);

        List<Integer> givenIds = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        updateNameAndAge(connection, givenIds);

        resultPrinting(connection);

    }

    private static void updateNameAndAge(Connection connection, List<Integer> givenIds) throws SQLException {
        String finalStatement = String.format(SET_MINION_NAME_AND_AGE, givenIds.stream()
                        .map(element -> "?")
                        .collect(Collectors.joining(", ")));

        PreparedStatement updateStatement = connection.prepareStatement(finalStatement);

        for (int i = 1; i <= givenIds.size(); i++) {
            updateStatement.setInt(i, givenIds.get(i - 1));
        }

        updateStatement.executeUpdate();
    }

    private static void resultPrinting(Connection connection) throws SQLException {
        PreparedStatement finalSelectStatement = connection.prepareStatement(SELECT_MINION_ID_NAME_AND_AGE);
        ResultSet finalResult = finalSelectStatement.executeQuery();

        while (finalResult.next()) {
            String currentName = finalResult.getString(MINION_NAME_COLUMN);
            int currentAge = finalResult.getInt(MINION_AGE_COLUMN);
            System.out.println(currentName + " " + currentAge);
        }
    }
}
