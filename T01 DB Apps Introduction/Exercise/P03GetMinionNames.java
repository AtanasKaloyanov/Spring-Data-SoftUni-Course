import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P03GetMinionNames {
    private static final String villainNameById = """
            SELECT `V`.`name` AS `villain_name`\s
            FROM `villains` AS `V`
            WHERE `V`.`id` = ?;""";

    private static final String minionsNameAndAgeByGivenVillainId = """
            SELECT DISTINCT `M`.`name` AS `minion_name`, `M`.`age`AS `minion_age`
            FROM `minions` AS `M`
            JOIN `minions_villains` AS `M_V`
            ON `M`.`id` = `M_V`.`minion_id`
            JOIN `villains` AS `V`
            ON `M_V`.`villain_id` = `V`.`id`
            WHERE `M_V`.`villain_id` =  ?;""";

    public static void main(String[] args) throws SQLException {
        Connection connection = P01GetConnection.getConnection();
        PreparedStatement villainStatement = connection.prepareStatement(villainNameById);

        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());
        villainStatement.setInt(1, villainId);

        ResultSet villainResult = villainStatement.executeQuery();

        if (!villainResult.next()) {
            System.out.println("No villain with ID 10 exists in the database.");
            return;
        }

        String villainName = villainResult.getString("villain_name");
        System.out.printf("Villain: %s%n", villainName);

        PreparedStatement minionsStatement = connection.prepareStatement(minionsNameAndAgeByGivenVillainId);
        minionsStatement.setInt(1, villainId);
        ResultSet minionsResult = minionsStatement.executeQuery();

        for (int i = 1; minionsResult.next(); i++) {
            String minionName = minionsResult.getString("minion_name");
            int minionAge = minionsResult.getInt("minion_age");
            System.out.printf("%d. %s %d%n", i, minionName, minionAge);
        }

        connection.close();
    }
}

