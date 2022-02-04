import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    private static final String[] COMMANDLIST = {"LIST", "ADD"};
    private static String command, param;
    
    /* TODO:
        Пример вывода списка Email, после ввода команды LIST в консоль:
        test@test.com
        hello@mail.ru
        - каждый адрес с новой строки
        - список должен быть отсортирован по алфавиту
        - email в разных регистрах считается одинаковыми
           hello@skillbox.ru == HeLLO@SKILLbox.RU
        - вывод на печать должен быть в нижнем регистре
           hello@skillbox.ru
        Пример вывода сообщения об ошибке при неверном формате Email:
        "Неверный формат email"
    */
    private static void resetVariables() {
        command = "";
        param = "";
    }

    private static void parseInput(String input) {
        resetVariables();
        String[] commandLine = input.split("\\s+", 2);
        command = commandLine[0];
        if (commandLine.length == 2) {
            param = commandLine[1];
        }
    }

    private static boolean isCorrectCommand(String command) {
        return Arrays.asList(COMMANDLIST).contains(command);
    }

    private static void executeCommand(EmailList emailList) {
        if (command.equals("LIST")) {
            System.out.println(emailList);
        } else if (command.equals("ADD")) {
            if (EmailList.isCorrectEmail(param.toLowerCase())) {
                emailList.add(param);
            } else {
                System.out.println(WRONG_EMAIL_ANSWER);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmailList emailList = new EmailList();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            
            //TODO: write code here
            parseInput(input);
            if (!isCorrectCommand(command)) {
                System.out.println("Unknown command");
                continue;
            }
            executeCommand(emailList);
        }
    }
}
