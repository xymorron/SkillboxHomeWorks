public class Main {

    public static void main(String[] args) {
        Basket basket1 = new Basket();
        basket1.add("Newspaper", 40);
        basket1.add("Bread", 5, 1, 400);
        basket1.add("Potatoes", 3, 15, 80);

        Basket basket2 = new Basket();
        basket2.add("Tomates", 40, 3, 30);
        basket2.add("Meat", 300, 2, 200);

        basket1.print("Basket1 contains:");
        basket2.print("Basket2 contains:");
        System.out.println();
        System.out.println("Basket1 avg price: " + basket1.getAvgPrice());
        System.out.println("Basket2 avg price: " + basket2.getAvgPrice());
        System.out.println("Total avg price:" + Basket.getTotalAvgPrice());
        basket1.clear();
        basket1.print("Cleared basket1 contains:");
        basket1.add("Newspaper", 0);
        basket1.print("Weight, price, itemsCount zeroed:");

    }
}
