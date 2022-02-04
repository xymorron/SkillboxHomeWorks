public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.add("Bread", 5, 1, 0.5);
        basket.add("Potatoes", 3, 1, 1.3);
        basket.print("Basket contains:");
        basket.clear();
        basket.print("Cleared basket contains:");
        basket.add("Milk", 40);
        basket.print("Weight zeroed:");

    }
}
