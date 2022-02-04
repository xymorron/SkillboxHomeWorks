public class Account {

    private long money;
    private String accNumber;
    private boolean blocked;

    public Account(String accNumber) {
        this.accNumber = accNumber;
        money = 0;
        blocked = false;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void block() {
        blocked = true;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    @Override
    public String toString() {
        return accNumber + "\t" + money + "\t" + (blocked ? "blocked" : "");
    }
}
