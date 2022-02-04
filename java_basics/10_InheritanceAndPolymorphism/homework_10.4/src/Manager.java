public class Manager extends HiredStaff{
    private int salary = 50000;
    private int sales;

    public Manager() {
        sales = (int) (115000 + 25000 * Math.random());
    }

    @Override
    public int getMonthSalary() {
        return (int) (salary + sales * 0.05);
    }

    public int getSales() {
        return sales;
    }

}
