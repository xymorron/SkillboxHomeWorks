import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long timeCounter;
        boolean isFind;
        System.out.println("Создание базы для поиска..");
        List<String> numbers = CoolNumbers.generateCoolNumbers();
        List<String> sortedNumbers = new ArrayList<>(numbers) {};
        Collections.sort(sortedNumbers);
        HashSet<String> hSet = new HashSet<>(numbers);
        TreeSet<String> tSet = new TreeSet<>(numbers);
        System.out.println("Создана база, количество записей: " + numbers.size());

        while (true) {
            System.out.println("Случайный номер из базы: " + numbers.get(1 + (int)(1999998 * Math.random())));
            System.out.println("Введите номер для поиска:");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            System.out.println("Поиск перебором(в цикле):");
            timeCounter = System.nanoTime();
            isFind = CoolNumbers.bruteForceSearchInList(numbers, input);
            timeCounter = System.nanoTime() - timeCounter;
            System.out.println("\tНомер " + (isFind?"":"не ") + "найден. Поиск занял " + timeCounter + " нс");

            System.out.println("Поиск перебором(через метод .contains):");
            timeCounter = System.nanoTime();
            isFind = CoolNumbers.bruteForceSearchInList2(numbers, input);
            timeCounter = System.nanoTime() - timeCounter;
            System.out.println("\tНомер " + (isFind?"":"не ") + "найден. Поиск занял " + timeCounter + " нс");

            System.out.println("Бинарный поиск:");
            timeCounter = System.nanoTime();
            isFind = CoolNumbers.binarySearchInList(sortedNumbers, input);
            timeCounter = System.nanoTime() - timeCounter;
            System.out.println("\tНомер " + (isFind?"":"не ") + "найден. Поиск занял " + timeCounter + " нс");

            System.out.println("Поиск в HashSet:");
            timeCounter = System.nanoTime();
            isFind = CoolNumbers.searchInHashSet(hSet, input);
            timeCounter = System.nanoTime() - timeCounter;
            System.out.println("\tНомер " + (isFind?"":"не ") + "найден. Поиск занял " + timeCounter + " нс");

            System.out.println("Поиск в TreeSet:");
            timeCounter = System.nanoTime();
            isFind = CoolNumbers.searchInTreeSet(tSet, input);
            timeCounter = System.nanoTime() - timeCounter;
            System.out.println("\tНомер " + (isFind?"":"не ") + "найден. Поиск занял " + timeCounter + " нс");

            System.out.println("Поиск завершен!");

        }
    }
}
