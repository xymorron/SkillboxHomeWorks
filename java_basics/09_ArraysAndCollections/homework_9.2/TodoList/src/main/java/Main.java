import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String[] COMMANDLIST = {"LIST", "ADD", "EDIT", "DELETE"};
    private static TodoList todoList = new TodoList();
    private static String command, param;
    private static int index;

    private static void resetVariables() {
        command = "";
        param = "";
        index = -1;
    }

    private static void parseInput(String input) {
        resetVariables();
        String[] commandLine = input.split("\\s+", 3);
        command = commandLine[0];
        if (commandLine.length >= 2) {
            try {
                index = Integer.parseInt(commandLine[1]);
            } catch (NumberFormatException e) {}
        }
        try {
            int splitParts = (index == -1) ? 2 : 3;
            param = input.split("\\s+", splitParts)[splitParts - 1];
        } catch (ArrayIndexOutOfBoundsException e) {}
    }

    private static boolean isCorrectCommand(String command) {
        return Arrays.asList(COMMANDLIST).contains(command);
    }

    private static void executeCommand(TodoList planner) {
        if (command.equals("LIST")) {
            System.out.println(planner);
        } else if (command.equals("ADD")) {
            planner.add(index, param);
        } else if (command.equals("EDIT")) {
            planner.edit(param, index);
        } else if (command.equals("DELETE")) {
            planner.delete(index);
        }
    }

    public static void main(String[] args) {
        // TODO: написать консольное приложение для работы со списком дел todoList
        Scanner scanner = new Scanner(System.in);
        TodoList planner = new TodoList();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            parseInput(input);
            if (!isCorrectCommand(command)) {
                System.out.println("Unknown command");
                continue;
            }
            executeCommand(planner);
        }
    }
}
