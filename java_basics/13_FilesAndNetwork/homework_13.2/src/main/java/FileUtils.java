import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        Path source = Paths.get(sourceDirectory);
        Path destination = Paths.get(destinationDirectory);
        try {
            Files.walk(source).forEach(file ->{
                Path newFile = destination.resolve(source.relativize(file));
                if (Files.isRegularFile(file))
                    copyFile(file, newFile);
                else
                    createDirectory(newFile);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDirectory(Path dirPath) {
        try {
            Files.createDirectories(dirPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(Path sourceFile, Path destinationFile) {
        try {
            Files.copy(sourceFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


