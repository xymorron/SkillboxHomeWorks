import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {

    private Map<String, Account> accounts;
    private final Random random = new Random();
    private long transferTries = 0;
    private long transferSuccess = 0;

    public Bank() {
        accounts = new ConcurrentHashMap<>();
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        transferTries++;
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        if (fromAccount.isBlocked() || toAccount.isBlocked())
            return;
        if (amount > 50000) {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)){
                    fromAccount.block();
                    toAccount.block();
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
                if (getBalance(fromAccountNum) > amount) {
                    fromAccount.setMoney(fromAccount.getMoney() - amount);
                    toAccount.setMoney(toAccount.getMoney() + amount);
                    transferSuccess++;
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.containsKey(accountNum) ? accounts.get(accountNum).getMoney() : 0;
    }

    public long getSumAllAccounts() {
        return accounts.keySet().stream()
                .map(id -> accounts.get(id).getMoney()).reduce(Long::sum).orElse(0L);
    }

    public void addAccount(Account newAccount){
        accounts.put(newAccount.getAccNumber(), newAccount);
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public long getTransferTries() {
        return transferTries;
    }

    public long getTransferSuccess() {
        return transferSuccess;
    }

    public long getBlockedCount() {
        return accounts.keySet().stream().filter(accId -> accounts.get(accId).isBlocked()).count();
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        accounts.keySet().forEach(id -> stringJoiner.add(accounts.get(id).toString()));
        return stringJoiner.toString();
    }
}
