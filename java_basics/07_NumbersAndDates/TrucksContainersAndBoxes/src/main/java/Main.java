import java.util.Scanner;

public class Main {
    public static final int CONTAINER_CAPACITY = 27;
    private static final int TRUCK_CAPACITY = 12 * CONTAINER_CAPACITY;
    public static void main(String[] args) {
        int truckCounter = 0;
        int containerCounter = 0;
        String newLine = System.lineSeparator();
        Scanner scanner = new Scanner(System.in);
        int boxes = scanner.nextInt();
        for (int i = 1; i <= boxes; i++){
            if (i % TRUCK_CAPACITY == 1){
                System.out.println("Грузовик: " + ++truckCounter);
            }
            if (i % CONTAINER_CAPACITY == 1) {
                System.out.println("\tКонтейнер: " + ++containerCounter);
            }
            System.out.println("\t\tЯщик: " + i);
        }
        System.out.println("Необходимо:"+ newLine +
                "грузовиков - " + truckCounter + " шт."+ newLine +
                "контейнеров - " + containerCounter + " шт.");
        // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам
        // пример вывода при вводе 2
        // для отступа используйте табуляцию - \t

        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */
    }

}
