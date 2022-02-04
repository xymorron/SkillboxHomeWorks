public class ProductTest {
    public static void main(String[] args) {
        Product milk = new Product("Pure milk", 511111544);
        System.out.println(milk.getName());
        System.out.println(milk.getBarCode());
        System.out.println("Price " + milk.getPrice());
        milk.setPrice(60);
        System.out.println("New price " + milk.getPrice());
    }
}
