public class Main {

  public static void main(String[] args) {
    int rub_pos, num_pos;
    int shift = 0;
    int summ = 0;
    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    rub_pos = text.indexOf(" рубл");
    while (rub_pos != -1) {
      num_pos = 1 + text.lastIndexOf(' ', rub_pos -1);
      summ += Integer.parseInt(text.substring(num_pos, rub_pos));
      shift = 1 + rub_pos;
      rub_pos = text.indexOf(" рубл", shift);
    }
    System.out.println(summ);
    //TODO: напишите ваш код, результат вывести в консоль
  }

}