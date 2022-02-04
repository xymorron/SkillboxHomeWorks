public class Product {
    private final String name;
    private int price = 0;
    private final long barCode;

    public Product(String name, long barCode) {
        this.name = name;
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getBarCode() {
        return barCode;
    }
}
