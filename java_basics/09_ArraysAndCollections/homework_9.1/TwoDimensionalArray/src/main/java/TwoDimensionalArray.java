public class TwoDimensionalArray {
    public static char symbol = 'X';

    public static char[][] getTwoDimensionalArray(int size) {
        char [][] x = new char[size][size];
        //TODO: Написать метод, который создаст двумерный массив char заданного размера.
        // массив должен содержать символ symbol по диагоналям, пример для size = 3
        // [X,  , X]
        // [ , X,  ]
        // [X,  , X]
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i == j) || (size == j + i + 1)) {
                    x[i][j] = 'X';
                } else {
                    x[i][j] = ' ';
                }
            }
        }
        return x;
    }
}
