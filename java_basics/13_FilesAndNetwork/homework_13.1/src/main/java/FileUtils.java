import java.io.File;
import java.util.Arrays;

public class FileUtils {
    public static long calculateFolderSize(String path) {
        long totalSize;
        File base = new File(path);
        if (base.isFile()) {
            totalSize = base.length();
        } else {
            try {
                totalSize = Arrays.stream(base.listFiles())
                        .map(file -> calculateFolderSize(file.getAbsolutePath()))
                        .reduce(Long::sum).orElse(0L);
            } catch (NullPointerException exception) {
                exception.printStackTrace();
                System.out.println("Can't get access to " + base.getAbsolutePath());
                return 0;
            }
        }
        return totalSize;
    }
}
