public class Basket {

    private static int basketsCount = 0;
    private static int totalBasketPrice = 0;
    private static double totalBasketWeight = 0;
    private static int totalItemsInBasketsCount = 0;
    private String items = "";
    private int itemsInBasketCount = 0;
    private int basketPrice = 0;
    private double basketWeight = 0;
    private int weightLimit;

    public int getBasketPrice() {
        return basketPrice;
    }

    public static int getBasketsCount() {
        return basketsCount;
    }

    public Basket() {
        increaseBasketsCount(1);
        items = "";
        this.weightLimit = 1000000;
    }

    public Basket(int weightLimit) {
        this();
        this.weightLimit = weightLimit;
    }

    public Basket(String items, int basketPrice) {
        this();
        this.items = this.items + items;
        this.basketPrice = basketPrice;
    }

    public static void increaseBasketsCount(int basketsCount) {
        Basket.basketsCount = Basket.basketsCount + basketsCount;
    }

    private void increaseBasketPrice(int price) {
        basketPrice += price;
        totalBasketPrice += price;
    }

    private void increaseBasketWeight(double weight) {
        basketWeight += weight;
        totalBasketWeight += weight;
    }

    private void increaseItemsInBasketCount(int itemsCount) {
        itemsInBasketCount += itemsCount;
        totalItemsInBasketsCount += itemsCount;
    }

    public double getAvgPrice() {
        return (basketPrice / itemsInBasketCount);
    }

    public static double getTotalAvgPrice() {
        return (totalBasketPrice / totalItemsInBasketsCount);
    }

    public void add(String name, int price) {
        add(name, price, 1, 0);
    }

    public void add(String name, int price, int itemsCount) {
        add(name, price, itemsCount, 0);
    }

    public void add(String name, int price, int itemsCount, double weight) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (basketWeight + itemsCount * weight >= weightLimit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
                itemsCount + " шт. - " + price + " rub - " + weight + " gr";

        increaseBasketPrice(itemsCount * price);
        increaseBasketWeight(itemsCount * weight);
        increaseItemsInBasketCount(itemsCount);

    }

    public void clear() {
        items = "";
        totalBasketWeight -= basketWeight;
        totalBasketPrice -= basketPrice;
        totalItemsInBasketsCount -= itemsInBasketCount;
        basketPrice = 0;
        basketWeight = 0;
        itemsInBasketCount = 0;
    }


    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println("\n" + title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
            System.out.println("Basket weight: " + basketWeight + " gr");
            System.out.println("Basket price: " + basketPrice + " rub");
            System.out.println("Items in basket: " + itemsInBasketCount);
        }
    }
}
