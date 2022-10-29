import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class P07PrintAllMinionNames {
    private static final String MINION_NAMES = "SELECT `name` FROM `minions`;";
    private static final String MINION_NAME_COLUMN = "name";

    public static void main(String[] args) throws SQLException {

        Connection connection = P01GetConnection.getConnection();
        PreparedStatement minionsStatement = connection.prepareStatement(MINION_NAMES);
        ResultSet namesResult = minionsStatement.executeQuery();

        ArrayDeque<String> names = new ArrayDeque<>();

        while(namesResult.next()) {
            names.offer(namesResult.getString(MINION_NAME_COLUMN));
        }

        while(names.size() > 2) {
            System.out.println(names.pollFirst());
            System.out.println(names.pollLast());
        }

        while(!names.isEmpty()) {
            System.out.println(names.pop());
        }

        connection.close();
    }
}
