
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sourcePath, destinationPath;
        for (;;) {
            System.out.println("Enter file or folder to copy:");
            sourcePath = scanner.nextLine().trim();
            System.out.println("Enter new destination:");
            destinationPath = scanner.nextLine().trim();
            FileUtils.copyFolder(sourcePath, destinationPath);
        }
    }
}
