import java.util.Scanner;

public class Main {
    public static final String NAME_NOT_KNOWN = "Такого имени в телефонной книге нет.%n" +
            "Введите номер телефона для абонента “%s”:%n";
    public static final String PHONENUMBER_NOT_KNOWN = "Такого номера нет в телефонной книге.%n" +
            "Введите имя абонента для номера  “%s”:%n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phonebook = new PhoneBook();
        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            //TODO: write code here
            if (input.equals("LIST")) {
                System.out.println(phonebook);
            } else if (PhoneBook.isName(input)) {
                if (phonebook.isKnownName(input)) {
                    System.out.println(phonebook.getPhonesByName(input));
                } else {
                    System.out.printf(NAME_NOT_KNOWN, input);
                    String number = scanner.nextLine();
                    if (PhoneBook.isPhoneNumber(number)) {
                        phonebook.addContact(number, input);
                        System.out.println("Контакт сохранен!");
                    } else {
                        System.out.println("Неверный формат ввода");
                    }
                }
            } else if (PhoneBook.isPhoneNumber(input)) {
                if (phonebook.isKnownNumber(input)) {
                    System.out.println(phonebook.getNameByPhone(input));
                } else {
                    System.out.printf(PHONENUMBER_NOT_KNOWN, input);
                    String name = scanner.nextLine();
                    if (PhoneBook.isName(name)) {
                        phonebook.addContact(input, name);
                        System.out.println("Контакт сохранен!");
                    } else {
                        System.out.println("Неверный формат ввода");
                    }
                }
            } else {
                System.out.println("Неверный формат ввода");
            }

        }
    }
}
