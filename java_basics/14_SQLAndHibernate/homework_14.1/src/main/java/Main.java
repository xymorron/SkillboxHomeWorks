import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "test";
        String pass = "test";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select course_name, \n" +
                    "count(*) / (1 + max(month(subscription_date)) - min(month(subscription_date))) as avg_sales \n" +
                    "from purchaselist group by course_name order by 2 desc;");
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                float avgSales = resultSet.getFloat("avg_sales");
                System.out.println(courseName + " - " + avgSales);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
