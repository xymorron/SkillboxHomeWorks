import java.util.List;
import java.util.Scanner;

public class MongoShop {
    private static MongoStorage mongoStorage;
    private static final List<String> COMMAND_LIST = List.of(
        "ADDSHOP",
        "ADDGOODS",
        "ADDSALE",
        "SALESTATS"
    );

    public static void main(String[] args) {
        mongoStorage = new MongoStorage("localhost:27017");
        System.out.println("Welcome to the awesome shop system!");
        showHelp();
        Scanner scanner = new Scanner(System.in);
        String input;
        while(!(input = scanner.nextLine()).equals("x")){
            String command = input.split(" ", 2)[0].toUpperCase();
            execCommand(command, input);
        }
        mongoStorage.quit();
        System.out.println("Bye!");
    }

    private static void showHelp() {
        System.out.println("Available commands:" + COMMAND_LIST);
        System.out.println("To quit enter 'x'.");
    }

    private static void execCommand(String command, String input) {
        String[] params;
        String shopName, productName;
        int productCost;
        switch (command) {
            case "SALESTATS":
                mongoStorage.saleStats();
                break;
            case "ADDSHOP":
                try {
                    shopName = input.split(" ", 2)[1];
                    mongoStorage.addShop(shopName);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    System.out.println("Not enough data!");
                }
                break;
            case "ADDGOODS":
                params = input.split(" ", 3);
                try {
                    productName = params[1];
                    productCost = Integer.parseInt(params[2]);
                    mongoStorage.addGoods(productName, productCost);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    System.out.println("Not enough data!");
                } catch (NumberFormatException exception) {
                    System.out.println("Wrong price format!");
                }
                break;
            case "ADDSALE":
                params = input.split(" ", 3);
                try {
                    productName = params[1];
                    shopName = params[2];
                    mongoStorage.addSale(productName, shopName);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    System.out.println("Not enough data!");
                }
                break;
            default:
                System.out.println("Wrong command!");
                showHelp();
        }
    }

}
