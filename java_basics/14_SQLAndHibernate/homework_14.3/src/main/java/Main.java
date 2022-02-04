import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            System.out.println("Введите id курса или -1 для завершения работы:");
            try {
                int courseId = Integer.parseInt(scanner.nextLine());
                if (courseId == -1)
                    break;
                Course course = session.get(Course.class, courseId);
                System.out.println("Название курса: " + course.getName());
                System.out.println("Преподаватель: " + course.getTeacher());
                System.out.println("Студенты: ");
                course.getStudents().forEach(s ->{
                    System.out.println("\t" + s);
                    Subscription subscription = session.get(Subscription.class,
                            new SubscriptionKey(s.getId(), course.getId()));
                    System.out.println("\t" + subscription.getSubscriptionDate().format(formatter));
                });
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        sessionFactory.close();
    }
}
