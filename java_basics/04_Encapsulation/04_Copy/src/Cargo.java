public class Cargo {
    private final Dimension dimension;
    private final int weight;
    private final String deliveryAddress;
    private final boolean overting;
    private final int regNumber;
    private final boolean fragile;

    public Dimension getDimension() {
        return dimension;
    }

    public int getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isOverting() {
        return overting;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public boolean isFragile() {
        return fragile;
    }

    public Cargo(Dimension dimension, int weight, String deliveryAddress,
                 boolean overting, int regNumber, boolean fragile) {
        this.dimension = dimension;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.overting = overting;
        this.regNumber = regNumber;
        this.fragile = fragile;
    }

    public Cargo setDimension(Dimension dimension) {
        return new Cargo(dimension, weight, deliveryAddress, overting, regNumber, fragile);
    }

    public Cargo setWeight(int weight) {
        return new Cargo(dimension, weight, deliveryAddress, overting, regNumber, fragile);
    }

    public Cargo setDeliveryAddress(String deliveryAddress) {
        return new Cargo(dimension, weight, deliveryAddress, overting, regNumber, fragile);
    }

    public void print() {
        dimension.print();
        System.out.println(weight);
        System.out.println(deliveryAddress);
        System.out.println(overting ? "overting" : "not overting");
        System.out.println(regNumber);
        System.out.println(fragile ? "fragile" : "not fragile");
    }



}
