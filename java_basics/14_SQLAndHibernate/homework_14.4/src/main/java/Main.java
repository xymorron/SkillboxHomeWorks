import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "test";
        String pass = "test";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select course_name, student_name \n" +
                    "from purchaselist");
            while (resultSet.next()) {
                String courseName = "\'" + resultSet.getString("course_name") + "\'";
                String studentName = "\'" + resultSet.getString("student_name") + "\'";

                String hqlCourseByName = "From " + Course.class.getSimpleName() + " Where name = " + courseName;
                Course course = (Course) session.createQuery(hqlCourseByName).getSingleResult();

                String hqlStudentByName = "From " + Student.class.getSimpleName() + " Where name = " + studentName;
                Student student = (Student) session.createQuery(hqlStudentByName).getSingleResult();

                session.save(new LinkedPurchaseList(new SubscriptionKey(student.getId(), course.getId())));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        transaction.commit();
        sessionFactory.close();
    }
}
