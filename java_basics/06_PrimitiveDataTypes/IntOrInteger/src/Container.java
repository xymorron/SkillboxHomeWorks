public class Container {
    private Integer count = 0;
    // or
    //private int count;

    public void addCount(int value) {
        count = count + value;
    }

    public int getCount() {
        return count;
    }
}
