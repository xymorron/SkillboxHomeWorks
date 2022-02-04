public class ReverseArray {

    //TODO: Напишите код, который меняет порядок расположения элементов внутри массива на обратный.
    public static String[] reverse (String[] strings){
        int len = strings.length;
        String tmp;
        for (int i = 0; i < len / 2; i++) {
            tmp = strings[len -1 - i];
            strings[len -1 - i] = strings[i];
            strings[i] = tmp;
        }
        return strings;
    }
}
