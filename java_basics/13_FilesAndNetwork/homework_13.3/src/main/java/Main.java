import java.util.Comparator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePaths = "Data/movementList.csv";
        double income, expense;
        Movements movements = new Movements((filePaths));
        expense = movements.getExpenseSum();
        income = movements.getIncomeSum();
        Map<String, Double> expenses  = movements.getExpensesByOrganisations();
        System.out.println("Сумма расходов: " + expense);
        System.out.println("Сумма доходов: " + income);
        System.out.println("Расходы по организациям: ");
        expenses.keySet().stream().sorted(Comparator.comparing(o -> expenses.get(o)).reversed())
                .forEach(o -> System.out.println(String.format("%-45s", o) + expenses.get(o)));
    }
}
