import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Scanner scanner = new Scanner(System.in);
        for (;;) {
            System.out.println("Введите id курса или -1 для завершения работы:");
            try {
                int courseId = Integer.parseInt(scanner.nextLine());
                if (courseId == -1)
                    break;
                Course course = session.get(Course.class, courseId);
                System.out.println(course.getName());
                System.out.println("На этом курсе учится " + course.getStudentsCount() + " студентов.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        sessionFactory.close();
    }
}
