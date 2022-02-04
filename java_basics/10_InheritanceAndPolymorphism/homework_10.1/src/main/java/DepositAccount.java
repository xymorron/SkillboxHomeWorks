import java.time.LocalDate;

class DepositAccount extends BankAccount {
    private LocalDate lastIncome;

    DepositAccount(double moneyAmount) {
        super();
        put(moneyAmount);
    }

    DepositAccount() {
        super();
    }

    private void setLastIncome() {
        this.lastIncome = LocalDate.now();
    }

    void put(double amountToPut) {
        setLastIncome();
        super.put(amountToPut);
    }

    boolean take(double amountToTake) {
        if (LocalDate.now().isAfter(lastIncome.plusDays(30))) {
            return super.take(amountToTake);
        }
        return false;
    }

}
