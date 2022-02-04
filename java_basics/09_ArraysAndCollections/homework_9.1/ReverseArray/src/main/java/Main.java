public class Main {

    public static void main(String[] args) {
        String line = "Каждый охотник желает знать, где сидит фазан";
        String[] strings = line.split("\\s");
        for (String word : ReverseArray.reverse(strings)) {
            System.out.println(word);
        }

    }
}
