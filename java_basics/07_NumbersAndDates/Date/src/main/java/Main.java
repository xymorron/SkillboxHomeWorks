import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 31;
        int month = 12;
        int year = 1990;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {
        LocalDate bDay = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - E").
                localizedBy(new Locale("en"));
        int age = 0;
        String newLine = System.lineSeparator();
        String outString = "";
        while (!bDay.isAfter(now)) {
            outString = outString + age++ + " - " + formatter.format(bDay) + newLine;
            bDay = bDay.plusYears(1);
        }
        System.out.println(outString);
        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue
        return outString;
    }
}
