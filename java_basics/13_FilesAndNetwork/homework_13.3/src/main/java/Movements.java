import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Movements {
    private final String dataPath;
    public Movements(String pathMovementsCsv) {
        dataPath = pathMovementsCsv;
    }

    public static String[] splitLine(String line) {
        char separator = ',';
        ArrayList<String> list = new ArrayList<>();
        boolean quoteOdds = true;
        int start = 0;
        char currentChar;
        for (int i = 0; i < line.length(); i++) {
            currentChar = line.charAt(i);
            if (currentChar == '"')
                quoteOdds = !quoteOdds;
            if (currentChar == separator && quoteOdds) {
                list.add(line.substring(start, i).replace("\"", ""));
                start = i + 1;
                continue;
            }
        }
        list.add(line.substring(start).replace("\"", ""));
        return list.toArray(new String[list.size()]);
    }

    public String[] getDataInColumn(int column) {
        List<String> list = new ArrayList<>();
        try {
            list = Files.lines(Paths.get(dataPath))
                    .map(line -> splitLine(line)[column])
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.toArray(new String[list.size()]);
    }

    public double getSumInColumn(int column) {
        double result = 0;
        result = Arrays.stream(getDataInColumn(column)).skip(1)
                .map(count ->{
                    try {
                        return Double.parseDouble(count.replace(',', '.'));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return 0d;
                    }
                })
                .reduce(Double::sum).orElse(0d);
        return result;
    }

    public Map getExpensesByOrganisations() {
        String[] payments = getDataInColumn(7);
        String[] descriptions = getDataInColumn(5);
        HashMap<String, Double> expenses = new HashMap<>();
        double payment;
        String organisation;
        for (int i = 1; i < payments.length; i++) {
            payment = Double.parseDouble(payments[i].replace(',', '.'));
            if (payment > 0) {
                organisation = getOrganisationFromDescription(descriptions[i]);
                payment += expenses.containsKey(organisation)? expenses.get(organisation): 0;
                expenses.put(organisation, payment);
            }
        }
        return expenses;
    }

    public static String getOrganisationFromDescription(String description) {
        String temp = description.substring(20, 60).trim().replace('/', '\\');
        temp = temp.substring(temp.indexOf('\\')).replace("\\","");
        return temp;
    }

    public double getExpenseSum() {
        return getSumInColumn(7);
    }

    public double getIncomeSum() {
        return getSumInColumn(6);
    }
}
