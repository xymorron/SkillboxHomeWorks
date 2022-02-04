import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.TreeSet;

public class EmailList {

    private TreeSet<String> emails = new TreeSet<>();

    public static boolean isCorrectEmail(String email) {
        return email.matches("[\\w]+@[\\w]+\\.[a-z]{2,3}");
    }

    public void add(String email) {
        // TODO: валидный формат email добавляется
        email = email.toLowerCase();
        if (isCorrectEmail(email)) {
            emails.add(email);
        }
    }

    public List<String> getSortedEmails() {
        // TODO: возвращается список электронных адресов в алфавитном порядке
        return new ArrayList<String>(emails);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (String email : emails) {
            joiner.add(email);
        }
        return joiner.toString();
    }
}
