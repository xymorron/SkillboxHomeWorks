import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      String result = "";
      String[] outFormats = new String[] {"Фамилия: %s%n", "Имя: %s%n", "Отчество: %s%n"};
      String pattern = "^[А-Яа-я-]+ [А-Яа-я-]+ [А-Яа-я-]+$";
      if (input.matches(pattern)) {
        String[] words = input.split(" ");
        for (int i = 0; i < 3; i++) {
          result += String.format(outFormats[i], words[i]);
        }
      } else {
        result = "Введенная строка не является ФИО";
      }
      System.out.println(result);
      //TODO:напишите ваш код тут, результат вывести в консоль.
      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
    }
  }

}