import java.util.*;

public class PhoneBook {
    private HashMap<String, String> contacts = new HashMap<>();


    public static boolean isName(String input) {
        return input.matches("^[А-Я][а-яА-Я ]*$");
    }

    public static boolean isPhoneNumber(String input) {
        return input.matches("^7[0-9]{10}$");
    }

    public boolean isKnownName(String name) {
        return contacts.containsValue(name);
    }

    public boolean isKnownNumber(String number) {
        return contacts.containsKey(number);
    }

    public void addContact(String phoneNumber, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (isPhoneNumber(phoneNumber) && isName(name)) {
            contacts.put(phoneNumber, name);
        }
    }

    public String getNameByPhone(String phoneNumber) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        if (isKnownNumber(phoneNumber)) {
            return contacts.get(phoneNumber) + " - " + phoneNumber;
        }
        return "";
    }

    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        StringJoiner joiner = new StringJoiner(", ");
        TreeSet<String> result = new TreeSet<>();
        if (isKnownName(name)) {
            for (String phoneNumber : contacts.keySet()) {
                if (contacts.get(phoneNumber).equals(name)) {
                    joiner.add(phoneNumber);
                }
            }
            result.add(name + " - " + joiner.toString());
        }
        return result;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet<String> result = new TreeSet<>();
        for (String phoneNumber : contacts.keySet()) {
            result.add(contacts.get(phoneNumber) + " - " + phoneNumber);
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        TreeMap<String, String> contactsOut = new TreeMap<>();
        for (String phoneNumber : contacts.keySet()) {
            String name = contacts.get(phoneNumber);
            String addidionalNumbers = contactsOut.containsKey(name) ? contactsOut.get(name) + ", " : "";
            contactsOut.put(name, addidionalNumbers + phoneNumber);
        }
        for (String name : contactsOut.keySet()) {
            joiner.add(name + " - " + contactsOut.get(name));
        }
        return joiner.toString();
    }
}