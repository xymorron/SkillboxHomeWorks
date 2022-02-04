import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {

  public static void main(String[] args) {
    LocalDate javaBDay = LocalDate.of(1995, 5, 23);
    LocalDate now = LocalDate.now();
    System.out.println(getPeriodFromBirthday(javaBDay));
    System.out.println(getPeriodFromBirthday(now));
    System.out.println(getPeriodFromBirthday(now.plusDays(1)));
    System.out.println(getPeriodFromBirthday(now.minusDays(1).minusMonths(1).minusYears(1)));
  }

  private static String getPeriodFromBirthday(LocalDate birthday) {
    LocalDate now = LocalDate.now();
    if (birthday.isAfter(now)) {
      return "This day has not come yet..";
    }
    long years, months, days;
    String resultString;
    years = birthday.until(now, ChronoUnit.YEARS);
    birthday = birthday.plusYears(years);
    months = birthday.until(now, ChronoUnit.MONTHS);
    birthday = birthday.plusMonths(months);
    days = birthday.until(now, ChronoUnit.DAYS);
    resultString = years + ((years == 1)? " year, ": " years, ");
    resultString += months + ((months == 1)? " month, ": " months, ");
    resultString += days + ((days == 1)? " day": " days");
    return resultString;
  }

}
