import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P06RemoveVillain {
    public static final String VILLAIN_NAME_SELECT = """
            SELECT `name`\s
            FROM `villains`
            WHERE `id` = ?;""";

    private static final String RELEASE_MINIONS_BY_VILLAIN_ID = """
            DELETE `M_V`\s
            FROM `minions_villains` AS `M_V`
            WHERE `villain_id` = ?;""";

    private static final String DELETE_VILLAIN_BY_ID = """
            DELETE `V`\s
            FROM `villains` AS `V`
            WHERE `id` = ?;""";

    public static final String NO_VILLAIN_WITH_THE_GIVEN_ID = "No such villain was found";
    private static final String VILLAIN_NAME = "name";
    private static final String VILLLAIN_DELETE_MESSAGE = "%s was deleted%n";
    private static final String MINIONS_RELEASE_MESSAGE = "%d minions released";

    public static void main(String[] args) throws SQLException {
        Connection connection = P01GetConnection.getConnection();

        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainNameStatement = connection.prepareStatement(VILLAIN_NAME_SELECT);
        villainNameStatement.setInt(1, villainId);
        ResultSet villainNameResult = villainNameStatement.executeQuery();

        if (!villainNameResult.next()) {
            System.out.println(NO_VILLAIN_WITH_THE_GIVEN_ID);
            return;
        }

        connection.setAutoCommit(false);

        try {
            PreparedStatement releaseMinionsStatement = connection.prepareStatement(RELEASE_MINIONS_BY_VILLAIN_ID);
            releaseMinionsStatement.setInt(1, villainId);
            int minionsCount = releaseMinionsStatement.executeUpdate();

            PreparedStatement villainDeletingStatement = connection.prepareStatement(DELETE_VILLAIN_BY_ID);
            villainDeletingStatement.setInt(1, villainId);
            villainDeletingStatement.executeUpdate();

            connection.commit();

            String villainName = villainNameResult.getString(VILLAIN_NAME);

            System.out.printf(VILLLAIN_DELETE_MESSAGE, villainName);
            System.out.printf(MINIONS_RELEASE_MESSAGE, minionsCount);
            
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }

        connection.close();
    }
}

