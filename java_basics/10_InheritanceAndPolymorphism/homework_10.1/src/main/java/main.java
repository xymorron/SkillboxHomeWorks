import org.checkerframework.checker.units.qual.C;

class main {
    public static void main(String[] args) {
        CardAccount vasya = new CardAccount(10000);
        DepositAccount sveta = new DepositAccount(500);
        System.out.printf("У Васи %.2f рублей, а у Светы %.2f рублей%n", vasya.getAmount(), sveta.getAmount());
        vasya.take(5000);
        System.out.printf("Вася снял 5000 рублей, и заплатил процент 50 рублей" +
                ", теперь у него %.2f рублей%n", vasya.getAmount());
        vasya.put(20000);
        System.out.printf("Вася получил зарплату и положил на счет 20000 рублей, " +
                "теперь у Васи %.2f рублей%n", vasya.getAmount());
        vasya.send(sveta, 8000);
        System.out.println("А еще Вася торчит Свете 8000, и отправляет ей их переводом. И платит процент за карту =)");
        System.out.printf("Теперь у Васи %.2f рублей, а у Светы %.2f рублей%n", vasya.getAmount(), sveta.getAmount());
        sveta.take(500);
        System.out.printf("Света попыталась снять 500 рублей на маникюр, но не смогла, у Светы депозит. " +
                "Поэтому у Светы сумма не поменялась - %.2f рублей", sveta.getAmount());
    }
}
