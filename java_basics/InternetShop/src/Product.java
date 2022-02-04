public class Product {
    private final String name;
    private final int price;
    private final long barCode;

    public Product(String name, int price, long barCode) {
        this.name = name;
        this.price = price;
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public long getBarCode() {
        return barCode;
    }

    public Product setName(String name) {
        return new Product(name, price, barCode);
    }

    public Product setPrice(int price) {
        return new Product(name, price, barCode);
    }
    public Product setBarCode(long barCode) {
        return new Product(name, price, barCode);
    }

    public String toString() {
        return name + price + barCode;
    }

}
