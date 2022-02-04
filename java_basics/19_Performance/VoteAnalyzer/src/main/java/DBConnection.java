import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DBConnection {

    private static Connection connection;
    private static StringBuilder insertQuery = new StringBuilder();
    private static final String DB_NAME = "voting";
    private static final String DB_USER = "test";
    private static final String DB_PASS = "test";
    private static final int MAX_QUERY_LEN = 30_000_000;
    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    private static boolean isThreadBusy = false;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + DB_NAME +
                        "?user=" + DB_USER + "&password=" + DB_PASS);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "`count` INT NOT NULL, " +
                    "PRIMARY KEY(id), " +
                    "UNIQUE KEY name_date(name(50), birthDate))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void shutdownExecutor() {
        executor.shutdown();
        try {
            executor.awaitTermination(180, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void executeMultiInsert() {
        String sql = "INSERT INTO voter_count(name, birthDate, count) " +
            "VALUES " + insertQuery.toString() +
            "ON DUPLICATE KEY UPDATE count = count + 1";
        Runnable task = () -> {
            isThreadBusy = true;
            long queryLen = sql.length();
            System.out.print("Pushing to database.. ");
            long timeStamp = System.currentTimeMillis();
            try {
                DBConnection.getConnection().createStatement().execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            timeStamp = (System.currentTimeMillis() - timeStamp) / 1000;
            System.out.println("Pushed query with len " + queryLen + " for " + timeStamp + " seconds");
            isThreadBusy = false;
        };
        while (isThreadBusy) {
            sleep(300);
        }
        executor.execute(new Thread(task));
        insertQuery.setLength(0);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        int queryLen = insertQuery.length();
        insertQuery.append((queryLen == 0 ? "" : ",") +
                "('" + name + "', '" + birthDay + "', 1)");
        if (queryLen > MAX_QUERY_LEN) {
            executeMultiInsert();
        }
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
        rs.close();
    }
}
