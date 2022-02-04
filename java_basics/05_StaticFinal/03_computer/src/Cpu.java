public class Cpu {
    private final int frequency;
    private final int coresCount;
    private final Cpu_manufacter manufacter;
    private final int weight;

    public int getFrequency() {
        return frequency;
    }

    public int getCoresCount() {
        return coresCount;
    }

    public Cpu_manufacter getManufacter() {
        return manufacter;
    }

    public int getWeight() {
        return weight;
    }
    public Cpu(int frequency, int coresCount, Cpu_manufacter manufacter, int weight) {
        this.frequency = frequency;
        this.coresCount = coresCount;
        this.manufacter = manufacter;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "frequency=" + frequency +
                ", coresCount=" + coresCount +
                ", manufacter=" + manufacter +
                ", weight=" + weight;
    }
}
