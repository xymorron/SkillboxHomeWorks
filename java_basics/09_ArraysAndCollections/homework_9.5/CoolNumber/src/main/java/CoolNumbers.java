import java.util.*;

public class CoolNumbers {
    public static final String LETTERS = "АВЕКМНОРСТУХ";
    public static final String NUMBERS = "0123456789";

    private static String getRNDLetters(String letters, int len) {
        short index;
        String result = "";
        for (int i = 0; i < len; i++) {
            index = (short) Math.round(Math.random() * (letters.length() - 1));
            result += letters.charAt(index);
        }
        return result;
    }

    public static String generateNumber() {
        return getRNDLetters(LETTERS, 1 ) + getRNDLetters(NUMBERS, 1).repeat(3) +
                getRNDLetters(LETTERS, 2) +
                "1".repeat((int) Math.round(Math.random())) + getRNDLetters(NUMBERS, 2) ;
    }

    public static List<String> generateCoolNumbers() {
        HashSet<String> numbers = new HashSet<>();
        ArrayList<String> result = new ArrayList<>();
//        numbers.add("О444ЕС30");
        while (numbers.size() < 2000000) {
            numbers.add(generateNumber());
        }
        result.addAll(numbers);
        return  result;
//        return Collections.emptyList();
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        for (String record : list) {
            if (record.equals(number)) {
                return true;
            }
        }
        return false;
    }

    public static boolean bruteForceSearchInList2(List<String> list, String number) {
        return list.contains(number);
    }


    public static boolean binarySearchInList(List<String> sortedList, String number) {
        int index = Collections.binarySearch(sortedList, number);
        return index >= 0;
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);
    }
}
