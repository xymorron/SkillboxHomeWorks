public class TopManager extends HiredStaff{
    private int salary = 100000;

    @Override
    public int getMonthSalary() {
        if (getCompany() != null) {
            return (getCompany().getIncome() <= 10000000) ? salary : (int) (salary * 2.5);
        }
        return 0;
    }
}
