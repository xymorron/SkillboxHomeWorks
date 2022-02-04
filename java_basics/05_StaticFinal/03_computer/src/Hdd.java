public class Hdd {
    private final HddType type;
    private final int capacity;
    private final int weight;

    public HddType getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getWeight() {
        return weight;
    }

    public Hdd(HddType type, int capacity, int weight) {
        this.type = type;
        this.capacity = capacity;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "HDD_type=" + type +
                ", capacity=" + capacity +
                ", weight=" + weight;
    }
}
