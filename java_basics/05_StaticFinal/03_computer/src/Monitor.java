public class Monitor {
    private final int size;
    private final Monitor_type type;
    private final int weight;

    public int getSize() {
        return size;
    }

    public Monitor_type getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public Monitor(int size, Monitor_type type, int weight) {
        this.size = size;
        this.type = type;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "size=" + size +
                ",Matrix_type=" + type +
                ", weight=" + weight;
    }
}
