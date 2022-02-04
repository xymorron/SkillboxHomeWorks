public class Main {
    public static void main(String[] args) {
        System.out.println("Dimension test:");
        Dimension dimension1 = new Dimension(3, 5, 7);
        dimension1.print();
        Dimension dimension2 = dimension1.setLength(30);
        dimension2.print();
        dimension1.print();
        System.out.println(dimension1.volume());
        System.out.println(dimension2.volume());
        System.out.println();
        Cargo cargo1 = new Cargo(dimension1, 5, "Moscow-city", true,
                295535, false);
        System.out.println("\nThis is cargo1\n");
        cargo1.print();
        System.out.println("\nThis is cargo1 address and dimension changing\n");
        cargo1.setDeliveryAddress("New-York").setDimension(dimension2).print();
        System.out.println("\ncargo1 is not changed \n");
        cargo1.print();

    }
}
