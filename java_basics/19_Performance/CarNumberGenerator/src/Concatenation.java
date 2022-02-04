
public class Concatenation {

    public static void main(String[] args) {
        long repeats = 10_000_000;
        System.out.println("Repeats " + repeats);

        long start = System.currentTimeMillis();
        for (int i = 0; i < repeats; i++) {
            String str = "";
            str += i;
            for (int j = 0; j < 50; str += j++);
        }
        System.out.println("Str concatenation test: ");
        System.out.println((System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < repeats; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(i);
            for (int j = 0; j < 50; builder.append(j++));
        }
        System.out.println("StringBuilder append test: ");
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
