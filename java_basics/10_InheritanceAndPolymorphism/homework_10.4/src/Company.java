import java.lang.reflect.Array;
import java.util.*;

public class Company {
    private Set<HiredStaff> staff = new HashSet<HiredStaff>();

    public void hire(HiredStaff employee) {
        staff.add(employee);
        employee.setCompany(this);
    }

    public void hireAll(Set<HiredStaff> employees) {
        for (HiredStaff employee : new HashSet<HiredStaff>(employees)) {
            hire(employee);
        }
    }

    public void fire(HiredStaff employee) {
        if (staff.contains(employee)) {
            staff.remove(employee);
            employee.setCompany(null);
        }
    }

    public void fireAll() {
        for (HiredStaff employee : new HashSet<HiredStaff>(staff)) {
            fire(employee);
        }
    }

    public int getIncome() {
        int income = 0;
        for (HiredStaff employee : staff) {
            if (employee instanceof Manager) {
                income += ((Manager) employee).getSales();
            }
        }
        return income;
    }

    public Set<HiredStaff> getStaff() {
        return new HashSet<HiredStaff>(staff);
    }

    public HiredStaff[] getTopSalaryStaff(int count) {
        if (count <= 0) return new HiredStaff[0];
        HiredStaff[] sortedStaff = new HiredStaff[staff.size()];
        sortedStaff = staff.toArray(sortedStaff);
        Arrays.sort(sortedStaff, Collections.reverseOrder());
        return Arrays.copyOfRange(sortedStaff, 0, Math.min(count, staff.size()));
    }

    public HiredStaff[] getLowestSalaryStaff(int count) {
        if (count <= 0) return new HiredStaff[0];
        HiredStaff[] sortedStaff = new HiredStaff[staff.size()];
        sortedStaff = staff.toArray(sortedStaff);
        Arrays.sort(sortedStaff);
        return Arrays.copyOfRange(sortedStaff, 0, Math.min(count, staff.size()));
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add("");
        result.add("\tCompany has income " + getIncome());
        result.add("\ttotal number of employees: " + staff.size());
        //result.add(" and have such staff:");
        //for (HiredStaff employee : staff) result.add(employee.toString());
        result.add("");
        return result.toString();
    }
}
