public class Main {
    public static void main(String[] args) {
        //Распечатайте сгенерированный в классе TwoDimensionalArray.java двумерный массив
        int size = 9;
        char[][] x = TwoDimensionalArray.getTwoDimensionalArray(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(x[i][j]);
            }
            System.out.println();
        }

    }
}
