import Model.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SearchEngine {
    public static void main(String[] args) {
        System.out.println("Hi there! This is my SearchEngine");
        String urlName = "http://radiomv.ru/";
        WebPage webPage = new WebPage(urlName);
        System.out.println("code: " + webPage.getCode());
        System.out.println("name: " + webPage.getPath());
        System.out.println("content: " + webPage.getContent());
        System.out.println("urls: " + webPage.getUrls());

//        webPage.query();
//        System.out.println("code: " + webPage.getCode());
//        System.out.println("name: " + webPage.getPath());
        System.out.println("content:");
        System.out.println(webPage.getContent());
//        webPage.getUrls().forEach(System.out::println);
        Page page = new Page();
        page.setCode(webPage.getCode());
        page.setPath(webPage.getPath());
        page.setContent(webPage.getContent());
        page.setSiteId(666);
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(page);
        transaction.commit();
        sessionFactory.close();


    }
}
