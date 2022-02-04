import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class MainMongo {

    private static final String CSV_PATH = "Data/mongo.csv";
    private final static String MONGO_LINK = "localhost:27017";

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient(MONGO_LINK);
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> students = database.getCollection("students");
        students.drop();
        importCSV(students, CSV_PATH);
        System.out.println("Всего студентов: " + students.countDocuments());
        int studentsOver40 = students.find(gt("age", 40)).into(new ArrayList<>()).size();
        System.out.println("Студентов старше 40 лет: " + studentsOver40);
        Document youngestStudent= students.find().sort(new Document("age", 1)).first();
        System.out.println("Самый молодой студент: " + youngestStudent.getString("name"));
        Document oldestStudent = students.find().sort(new Document("age", -1)).first();
        System.out.println("Список курсов самого старого студента: " +
                oldestStudent.getList("courses", String.class));
    }

    private static void importCSV(MongoCollection collection, String csvPath) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(CSV_PATH))) {
            csvReader.lines().forEach(line ->{
                String[] fields = line.split(",", 3);
                String name = fields[0];
                int age = Integer.parseInt(fields[1]);
                String[] courses = fields[2].substring(1, fields[2].length() - 1).split(",");
                insertToMongo(collection, name, age, courses);
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertToMongo(MongoCollection<Document> students, String name, int age, String[] courses) {
        Document document = new Document();
        document.put("name", name);
        document.put("age", age);
        document.put("courses", Set.of(courses));
        students.insertOne(document);
    }
}
