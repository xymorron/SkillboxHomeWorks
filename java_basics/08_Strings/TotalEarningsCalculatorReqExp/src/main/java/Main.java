import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

  }

  public static int calculateSalarySum(String text){
    //TODO: реализуйте метод
    int summ = 0;
    Pattern patter = Pattern.compile("\\d+");
    Matcher matcher = patter.matcher(text);
    while (matcher.find()) {
      summ += Integer.parseInt(text.substring(matcher.start(), matcher.end()));
    }
    return summ;
  }

}