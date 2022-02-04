import java.util.*;

public class Main {
    private static final int ACCOUNTS_COUNT = 30;
    private static final int THREADS_COUNT = 10;
    private static final int CYCLES_COUNT = 100;
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Bank bank = new Bank();
        for (int i = 0; i < ACCOUNTS_COUNT; i++){
            Account account = new Account(String.valueOf(i));
            account.setMoney(100000);
            bank.addAccount(account);
        }
        Random rnd = new Random();
        System.out.println("Bank stats before: ");
        System.out.println("Total accounts number: " + bank.getNumberOfAccounts());
        System.out.println("Total sum on accounts: " + bank.getSumAllAccounts());
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREADS_COUNT; i++)
            threads.add(new Thread(() -> {
                for (int j = 0; j < CYCLES_COUNT; j++) {
                    String accountFrom = String.valueOf(rnd.nextInt(ACCOUNTS_COUNT));
                    String accountTo = String.valueOf(rnd.nextInt(ACCOUNTS_COUNT));
                    int amount = rnd.nextInt((int) (50000 / 0.95));
                    bank.transfer(accountFrom, accountTo, amount);
                }
                System.out.println("Duration: " + (System.currentTimeMillis() - start));
            }));
        threads.forEach(Thread::start);

        while (threads.stream().anyMatch(t -> t.getState() != Thread.State.TERMINATED)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Bank stats after: ");
        System.out.println("Total accounts number: " + bank.getNumberOfAccounts());
        System.out.println("Total sum on accounts: " + bank.getSumAllAccounts());
        System.out.println("Total transfer tries: " + bank.getTransferTries());
        System.out.println("Total successfull transfer tries: " + bank.getTransferSuccess());
        System.out.println("Total blocked accounts: " + bank.getBlockedCount());
    }
}
