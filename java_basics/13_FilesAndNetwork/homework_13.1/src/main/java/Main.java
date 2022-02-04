import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long itemSize;
        String path;
        for (;;) {
            System.out.println("Enter file or folder to calculate size:");
            path = scanner.nextLine().trim();
            if (path.equals("0")) break;
            itemSize = FileUtils.calculateFolderSize(path);
            System.out.println(goodLookSize(itemSize));
        }
    }

    private static String goodLookSize(long size) {
        long KB = 1024;
        long MB = 1024 * KB;
        long GB = 1024 * MB;
        if (size > 10 * GB) return (size / GB + " GB");
        if (size > 10 * MB) return (size / MB + " MB");
        if (size > 10 * KB) return (size / KB + " KB");
        return size + " bytes";
    }
}
