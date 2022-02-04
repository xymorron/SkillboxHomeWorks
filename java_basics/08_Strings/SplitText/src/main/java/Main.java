import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

  }

  public static String splitTextIntoWords(String text) {
    String regex = "[a-zA-Z’]+";
    StringJoiner result = new StringJoiner((System.lineSeparator()));
    String word;
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      word = text.substring(matcher.start(), matcher.end());
      result.add(word);
    }
    return result.toString();
  }

}