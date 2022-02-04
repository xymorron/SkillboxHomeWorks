class BankAccount {
  private double moneyAmount;

  BankAccount(double moneyAmount) {
    this();
    put(moneyAmount);
  }

  BankAccount() {
    moneyAmount = 0;
  }

  boolean send(BankAccount receiver, double amount) {
    boolean transactionResult = take(amount);
    if (transactionResult) {
      receiver.put(amount);
    }
    return transactionResult;
  }

  double getAmount() {
    return moneyAmount;
  }

  void put(double amountToPut) {
    if (amountToPut > 0){
      moneyAmount += amountToPut;
    }
  }

  boolean take(double amountToTake) {
    if (amountToTake > 0 && amountToTake <= moneyAmount) {
      moneyAmount -= amountToTake;
      return true;
    }
    return false;
  }
}
