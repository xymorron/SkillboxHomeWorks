import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if (components.length != 4) throw new IllegalArgumentException("wrong data format");
        if (!isPhoneNumber(components[INDEX_PHONE])) throw new IllegalArgumentException("wrong phone format");
        if (!isCorrectEmail(components[INDEX_EMAIL])) throw new IllegalArgumentException("wrong email format");
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public static boolean isCorrectEmail(String email) {
        return email.matches("[\\w\\.]+@[\\w]+\\.[a-z]{2,3}");
    }

    public static boolean isPhoneNumber(String input) {
        return input.matches("^\\+7[0-9]{10}$");
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}