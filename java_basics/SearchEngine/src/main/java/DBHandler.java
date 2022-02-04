import Model.Page;
import Model.Site;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBHandler {
    private static Session session;
    private static SessionFactory sessionFactory;

    private static Session getSession() {
        if (session == null) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
            session = sessionFactory.openSession();
        }
        return session;
    }

    public static void saveToDB(Page page) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(page);
        transaction.commit();
    }

    public static void saveToDB(Site site) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(site);
        transaction.commit();
    }

    public static void close() {
        sessionFactory.close();
    }
}
