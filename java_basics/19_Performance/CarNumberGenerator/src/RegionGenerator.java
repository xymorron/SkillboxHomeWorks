import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RegionGenerator implements Runnable{

    private int regionCode;

    public RegionGenerator(int regionCode) {
        this.regionCode = regionCode;
    }

    @Override
    public void run() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("res/regions/" + regionCode + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        String region = padNumber(regionCode, 2);
        StringBuilder builder= new StringBuilder();
        for (int number = 1; number < 1000; number++) {
            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter);
                        builder.append(padNumber(number, 3));
                        builder.append(secondLetter);
                        builder.append(thirdLetter);
                        builder.append(region);
                        builder.append('\n');
                    }
                }
            }
        }
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }

    private static String padNumber2(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < padSize; i++) {
            builder.append('0');
        }
        builder.append(numberStr);
        return builder.toString();
    }



}
