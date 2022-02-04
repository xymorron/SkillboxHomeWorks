public class Ram {
    private final RamType type;
    private final int capacity;
    private final int weight;

    public RamType getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getWeight() {
        return weight;
    }

    public Ram(RamType type, int capacity, int weight) {
        this.type = type;
        this.capacity = capacity;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "RAM_type=" + type +
                ", capacity=" + capacity +
                ", weight=" + weight;
    }
}
