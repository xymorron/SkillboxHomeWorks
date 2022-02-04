import java.util.Locale;
import java.util.Scanner;

public class Main {

  public static boolean isCorrectName(String word) {
    int charNum;
    if (word.isEmpty()) {
      return false;
    }
    word = word.toLowerCase();
    for (int i = 0; i < word.length(); i++) {
      charNum = word.charAt(i);
      if ((charNum > 1105 || charNum < 1072) && charNum !='-'){
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String result = "";
      String[] outFormats = new String[] {"Фамилия: %s%n", "Имя: %s%n", "Отчество: %s%n"};
      boolean error = false;
      String nextWord;
      int spacePosition = 0;
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      //TODO:напишите ваш код тут, результат вывести в консоль.
      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
      for (int i = 0; i < 3; i++){
        input = input.strip();
        spacePosition = input.indexOf(' ');
        if (spacePosition == -1) {
          spacePosition = input.length();
        }
        nextWord = input.substring(0, spacePosition);
        if (!isCorrectName(nextWord)) {
          error = true;
          break;
        }
        result += String.format(outFormats[i], nextWord);
        input = input.substring(spacePosition).strip();
      }
      if (!input.isEmpty()) {
        error = true;
      }
      if (error) {
        result = "Введенная строка не является ФИО";
      }
      System.out.println(result);
    }
  }

}