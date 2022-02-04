public class IntPairs {
    private int i1, i2;

    public IntPairs(int i1, int i2) {
        this.i1 = i1;
        this.i2 = i2;
    }

    public int summ(){
        return i1 + i2;
    }

    public int mult() {
        return i1 * i2;
    }

    public int max() {
        if (i1 > i2) {
            return i1;
        }
        return i2;
    }

    public int min() {
        if (i1 < i2) {
            return i1;
        }
        return i2;
    }

    public void print() {
        System.out.println("(" + i1 + ", " + i2 + ")");
    }
}
