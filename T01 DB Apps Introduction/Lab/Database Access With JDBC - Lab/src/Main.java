import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement statement = connection.prepareStatement("SELECT `user_name`, `first_name`, `last_name`, COUNT(`U_G`.`game_id`) AS `games_count`\n" +
                "FROM `users` AS `U`\n" +
                "JOIN `users_games` AS `U_G`\n" +
                "ON `U`.`id` = `U_G`.`user_id`\n" +
                "WHERE `U`.`user_name` = ?\n" +
                "GROUP BY `U`.`id`;");

        Scanner scanner = new Scanner(System.in);
         String userName = scanner.nextLine();

         statement.setString(1, userName);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            String username = rs.getString("user_name");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Integer gamesCount = rs.getInt("games_count");

            System.out.printf("User: %s\n" +
                    "%s %s has played %d games\n", username, firstName, lastName, gamesCount);
        } else {
            System.out.println("No such user exists");
        }
    }
}
