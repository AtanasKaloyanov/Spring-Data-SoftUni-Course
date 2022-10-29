import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P04AddMinion {
    private static final String GET_TOWN_ID_BY_NAME = """
            SELECT `T`.`id`
            FROM `towns` AS `T`
            WHERE `T`.`name` = ?;""";

    private static final String INSERT_INTO_TOWNS =
            "INSERT INTO `towns` (`name`) \n" +
                    "VALUES (?); ";

    private static final String GET_VILLAIN_ID_BY_NAME = """
            SELECT `V`.`id`\s
            FROM `villains` AS `V`
            WHERE `V`.`name` = ?;""";

    private static final String INSERT_INTO_VILLAINS = "INSERT INTO  `villains`(`name`, `evilness_factor`)\n" +
            "VALUES (?, 'evil');";

    private static final String INSERT_INTO_MINIONS = "INSERT INTO  `minions`(`name`, `age`, `town_id`)\n" +
            "VALUES(?, ?, ?);";

    private static final String GET_MINION_ID = """
            SELECT `id` FROM `minions`
            ORDER BY `id` DESC
            LIMIT ?;""";

    private static final String INSERT_INTO_MINIONS_VILLAINS = "INSERT INTO `minions_villains`(`minion_id`, `villain_id`)\n" +
            "VALUES(? , ?);";

    private static final String INSERT_TOWN_MESSAGE = "Town %s was added to the database.%n";
    private static final String TOWN_COLUMN_ID = "id";
    private static final String INSERT_VILLAIN_MESSAGE = "Villain %s was added to the database.%n";
    private static final String VILLLAIN_COLUMN_ID = "id";
    private static final String MINIONS_COLUMN_ID = "id";
    private static final String INSERT_MINIONS_TO_VILLAIN_MESSAGE = "Successfully added %s to be minion of %s.";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String[] minionInfo = scanner.nextLine().split(" ");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];

        String villainName = scanner.nextLine().split(" ")[1];

        Connection connection = P01GetConnection.getConnection();

        int townId = getTownId(connection, minionTown);

        int villainId = getVillainId(connection, villainName);

        int minionId = getMinionId(minionName, minionAge, connection, townId);

        minionToVillainSetting(minionName, villainName, connection, villainId, minionId);

        connection.close();
    }

    private static void minionToVillainSetting(String minionName, String villainName, Connection connection, int villainId, int minionId) throws SQLException {
        PreparedStatement minionToVillainStatement = connection.prepareStatement(INSERT_INTO_MINIONS_VILLAINS);
        minionToVillainStatement.setInt(1, minionId);
        minionToVillainStatement.setInt(2, villainId);
        minionToVillainStatement.executeUpdate();
        System.out.printf(INSERT_MINIONS_TO_VILLAIN_MESSAGE, minionName, villainName);
    }

    private static int getMinionId(String minionName, int minionAge, Connection connection, int townId) throws SQLException {
        PreparedStatement insertMinionStatement = connection.prepareStatement(INSERT_INTO_MINIONS);
        insertMinionStatement.setString(1, minionName);
        insertMinionStatement.setInt(2, minionAge);
        insertMinionStatement.setInt(3, townId);
        insertMinionStatement.executeUpdate();

        PreparedStatement minionStatement = connection.prepareStatement(GET_MINION_ID);
        minionStatement.setInt(1, 1);
        ResultSet minionIdResult = minionStatement.executeQuery();
        minionIdResult.next();
        int minionId = minionIdResult.getInt(MINIONS_COLUMN_ID);

        return minionId;
    }

    private static int getVillainId(Connection connection, String villainName) throws SQLException {
        PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_ID_BY_NAME);
        villainStatement.setString(1, villainName);
        ResultSet villainResult = villainStatement.executeQuery();

        if (!villainResult.next()) {
            PreparedStatement insertVillainStatement = connection.prepareStatement(INSERT_INTO_VILLAINS);
            insertVillainStatement.setString(1, villainName);
            insertVillainStatement.executeUpdate();
            System.out.printf(INSERT_VILLAIN_MESSAGE, villainName);
        }

        PreparedStatement newVillainStatement = connection.prepareStatement(GET_VILLAIN_ID_BY_NAME);
        newVillainStatement.setString(1, villainName);
        ResultSet newVillainResult = newVillainStatement.executeQuery();
        newVillainResult.next();
        int villainId = newVillainResult.getInt(VILLLAIN_COLUMN_ID);

        return villainId;
    }

    private static int getTownId(Connection connection, String minionTown) throws SQLException {
        PreparedStatement townStatement = connection.prepareStatement(GET_TOWN_ID_BY_NAME);
        townStatement.setString(1, minionTown);
        ResultSet townResult = townStatement.executeQuery();

        if (!townResult.next()) {
            PreparedStatement insertTownStatement = connection.prepareStatement(INSERT_INTO_TOWNS);
            insertTownStatement.setString(1, minionTown);
            insertTownStatement.executeUpdate();
            System.out.printf(INSERT_TOWN_MESSAGE, minionTown);
        }

        PreparedStatement newTownStatement = connection.prepareStatement(GET_TOWN_ID_BY_NAME);
        newTownStatement.setString(1, minionTown);
        ResultSet newTownResult = newTownStatement.executeQuery();
        newTownResult.next();

        int townID = newTownResult.getInt(TOWN_COLUMN_ID);

        return townID;
    }
}
