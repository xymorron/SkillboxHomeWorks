import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      input = input.replaceAll("[^0-9]", "");
      input = input.replaceAll("^8", "7");
      if (input.length() == 10) {
        input = '7' + input;
      }
      if (input.matches("7[0-9]{10}")) {
        System.out.println(input);
      } else {
        System.out.println("Неверный формат номера");
      }

      //TODO:напишите ваш код тут, результат вывести в консоль.
    }
  }

}
