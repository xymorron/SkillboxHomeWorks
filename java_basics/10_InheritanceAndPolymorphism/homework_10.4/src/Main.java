import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        HiredStaff joe = new Operator();
        HiredStaff jane = new Manager();
        Company company1 = new Company();
        Company company2 = new Company();
        company1.hire(joe);
        company1.hire(jane);
        System.out.println("company1: " + company1);
        System.out.println("New company2 absorbs company1:");
        company2.hireAll(company1.getStaff());
        System.out.println("company1: " + company1);
        System.out.println("company2: " + company2);
        System.out.println("company2 fire all the staff");
        company2.fireAll();
        System.out.println("company2: " + company2);
        System.out.println("company2 hiring 180 Operators, 80 Managers, 10 TopManagers.");
        for (int i = 0; i < 180; i++) {
            company2.hire(new Operator());
        }
        for (int i = 0; i < 80; i++) {
            company2.hire(new Manager());
        }
        for (int i = 0; i < 10; i++) {
            company2.hire(new TopManager());
        }
        System.out.println("company2: " + company2);
        System.out.println("\t\t15TopSalaries:");
        for (HiredStaff employee : company2.getTopSalaryStaff(15)) {
            System.out.println(employee);
        }
        System.out.println("\t\t30LowestSalaries:");
        for (HiredStaff employee : company2.getLowestSalaryStaff(30)) {
            System.out.println(employee);
        }
        Set<HiredStaff> company2Staff = company2.getStaff();
        int countToFire = company2Staff.size() / 2;
        for (HiredStaff employee : company2Staff) {
            company2.fire(employee);
            countToFire--;
            if (countToFire <=0) break;
        }

        System.out.println("company2: " + company2);
        System.out.println("\t\t15TopSalaries:");
        for (HiredStaff employee : company2.getTopSalaryStaff(15)) {
            System.out.println(employee);
        }
        System.out.println("\t\t30LowestSalaries:");
        for (HiredStaff employee : company2.getLowestSalaryStaff(30)) {
            System.out.println(employee);
        }


    }

}
