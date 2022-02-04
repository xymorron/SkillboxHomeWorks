public class Keyboard {
    private final KeyboardType type;
    private final boolean backLight;
    private final int weight;

    public KeyboardType getType() {
        return type;
    }

    public boolean isBackLight() {
        return backLight;
    }

    public int getWeight() {
        return weight;
    }

    public Keyboard(KeyboardType type, boolean backLight, int weight) {
        this.type = type;
        this.backLight = backLight;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Keyboard_type=" + type +
                (backLight?", with_":", without_") + "backLight" +
                ", weight=" + weight;
    }
}
