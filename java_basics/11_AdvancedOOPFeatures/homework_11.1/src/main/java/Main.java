import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        sortBySalaryAndAlphabet(staff);
        for (Employee employee : staff) {
            System.out.println(employee);
        }

    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        Collections.sort(staff, Comparator
                .comparing(Employee::getSalary)
                .thenComparing(Employee::getName));
    }
}