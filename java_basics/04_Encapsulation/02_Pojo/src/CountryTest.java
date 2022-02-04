public class CountryTest {
    public static void main(String[] args) {
        Country vatikan = new Country("Vatikan");
        vatikan.setPopulation(799);
        vatikan.setArea(44);
        vatikan.setCapital("Vatikan-City");
        vatikan.setSeaAccessibility(false);

        System.out.println("Country name: " + vatikan.getName());
        System.out.println("Population: " + vatikan.getPopulation());
        System.out.println("Area: " + vatikan.getArea());
        System.out.println("Capital: " + vatikan.getCapital());
        System.out.println("Sea available: " + vatikan.getSeaAccessibility());
        }

}
