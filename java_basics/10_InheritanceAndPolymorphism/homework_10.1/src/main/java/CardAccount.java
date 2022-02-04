class CardAccount extends BankAccount {
    private static double commissionPercent = 1.0 / 100.0;

    CardAccount(double moneyAmount) {
        super(moneyAmount);
    }

    CardAccount() {
        super(0);
    }

    boolean take(double amountToTake) {
        return super.take(amountToTake * (1.0 + commissionPercent));
    }

}
