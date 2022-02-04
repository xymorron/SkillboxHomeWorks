public abstract class HiredStaff implements Employee, Comparable<HiredStaff>{
    private Company company;

    public void setCompany(Company company) {
        if (isEmployed()){
            this.company.fire(this);
        }
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public boolean isEmployed() {
        return this.company != null;
    }


    public int compareTo(HiredStaff o) {
        return getMonthSalary() - o.getMonthSalary();
    }

    @Override
    public String toString() {
        if (isEmployed()){
            return this.getClass().getSimpleName() + " is employed with month salary "
                    + getMonthSalary();
        }
        return " is unemployed";
    }
}
