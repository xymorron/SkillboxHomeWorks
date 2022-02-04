public class Country {
    private String name;
    private int population;
    private int area;
    private String capital;
    private boolean seaAccessibility;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public boolean getSeaAccessibility() {
        return seaAccessibility;
    }

    public void setSeaAccessibility(boolean seaAccessibility) {
        this.seaAccessibility = seaAccessibility;
    }

}
