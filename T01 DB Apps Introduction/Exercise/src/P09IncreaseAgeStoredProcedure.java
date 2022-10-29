import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P09IncreaseAgeStoredProcedure {
    private static final String SELECT_MINION_INFO_BY_ID = """
            SELECT `id`, `name`, `age`\s
            FROM `minions`
            WHERE `id` = ?;""";

    private static final String PROCEDURE_CALLING = "CALL `usp_get_older`(?);";
    private static final String MINION_COLUMN_NAME = "name";
    private static final String MINION_COLUMN_AGE = "age";
    private static final String WRONG_ID_MESSAGE = "There is no minion with id %d. You can insert another id, maybe it will be correct!";

    public static void main(String[] args) throws SQLException {
        Connection connection = P01GetConnection.getConnection();

        Scanner scanner = new Scanner(System.in);
        int givenNumber = Integer.parseInt(scanner.nextLine());

        ResultSet minionsResult = getResultSet(connection, givenNumber);

        if (!minionsResult.next()) {
            System.out.printf(WRONG_ID_MESSAGE, givenNumber);
            connection.close();
            return;
        }

        updateAge(connection, givenNumber);

        ResultSet printResult = getUpdatedResultSet(connection, givenNumber);

        finalPrinting(printResult);

        connection.close();
    }

    private static void finalPrinting(ResultSet printResult) throws SQLException {
        while (printResult.next()) {
            String currentName = printResult.getString(MINION_COLUMN_NAME);
            int currentAge = printResult.getInt(MINION_COLUMN_AGE);

            System.out.println(currentName + " " + currentAge);
        }
    }

    private static ResultSet getUpdatedResultSet(Connection connectiion, int givenNumber) throws SQLException {
        PreparedStatement printStatement = connectiion.prepareStatement(SELECT_MINION_INFO_BY_ID);
        printStatement.setInt(1, givenNumber);
        ResultSet printResult = printStatement.executeQuery();
        return printResult;
    }

    private static void updateAge(Connection connectiion, int givenNumber) throws SQLException {
        PreparedStatement procedureStatement = connectiion.prepareStatement(PROCEDURE_CALLING);
        procedureStatement.setInt(1, givenNumber);
        procedureStatement.executeUpdate();
    }

    private static ResultSet getResultSet(Connection connectiion, int givenNumber) throws SQLException {
        PreparedStatement minionsStatement = connectiion.prepareStatement(SELECT_MINION_INFO_BY_ID);
        minionsStatement.setInt(1, givenNumber);
        return minionsStatement.executeQuery();
    }
}

//DELIMITER ###
//
//CREATE PROCEDURE `usp_get_older`(`minion_id` INT)
//
//BEGIN
//UPDATE `minions`
//SET `age` = `age` + 1
//WHERE `id` = `minion_id`;
//END
//###
