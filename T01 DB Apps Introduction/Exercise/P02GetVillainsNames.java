import java.sql.*;
import java.util.Properties;

public class P02GetVillainsNames {
    private static final String sqlStatement = "SELECT `V`.`name`,  COUNT(DISTINCT `M_V`.`minion_id`) AS `minions_count`" +
            " FROM `villains` AS `V`" +
            " JOIN `minions_villains` AS `M_V`" +
            " ON `V`.`id` = `M_V`. `villain_id`" +
            " GROUP BY `V`.`id`" +
            " HAVING `minions_count` > ?" +
            " ORDER BY `minions_count` DESC;";

    public static void main(String[] args) throws SQLException {

        Connection connection = P01GetConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement(sqlStatement);

        statement.setInt(1, 15);
        ResultSet resultset = statement.executeQuery();

        while(resultset.next()) {
            String villailName = resultset.getString("name");
            int minionsCount = resultset.getInt("minions_count");
            System.out.printf("%s %d%n", villailName, minionsCount);
        }
        connection.close();
    }
}

