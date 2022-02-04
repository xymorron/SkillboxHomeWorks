public class IntPairsTest {
    public static void main(String[] args) {
        int i1 = 2;
        int i2 = 9;
        IntPairs pair = new IntPairs(i1, i2);
        pair.print();
        System.out.println("Sum of pair: " + pair.summ());
        System.out.println("Mult of pair: " + pair.mult());
        System.out.println("Max of pair: " + pair.max());
        System.out.println("Min of pair: " + pair.min());
    }
}
