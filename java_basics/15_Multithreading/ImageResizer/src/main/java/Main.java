import java.io.File;

public class Main {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        int cores = Runtime.getRuntime().availableProcessors();
        int newWidth = 300;
        String srcFolder = "/users/sortedmap/Desktop/src";
        String dstFolder = "/users/sortedmap/Desktop/dst";
        srcFolder = "data\\src";
        dstFolder = "data\\dst";
        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();
        int imgPerCoreCount = files.length / cores + 1;
        File[][] filesPerCore = new File[cores][];
        for (int i = 0; i < cores; i++) {
            int currentFilesCount = (i == cores - 1) ? files.length - (cores - 1) * imgPerCoreCount : imgPerCoreCount;
            filesPerCore[i] = new File[currentFilesCount];
            System.arraycopy(files, i * imgPerCoreCount, filesPerCore[i], 0, filesPerCore[i].length);
        }
        for (int i = 0; i < cores; i++)
            new Thread(new ImageResizer(filesPerCore[i], newWidth, dstFolder, start)).run();
    }
}
