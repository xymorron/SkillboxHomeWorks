import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String filePath = "data\\sitemap.txt";
        String startAddress = "https://skillbox.ru";
        StringJoiner joiner = new StringJoiner("\n");
        Url head = new Url(startAddress);
        Set<Url> siteMap = new ForkJoinPool().invoke(new UrlParseTask(head));

        for (Url url : siteMap.stream().sorted().collect(Collectors.toList())) {
            int slashCount = url.getUrlName().replaceAll("[^/]", "").length();
            joiner.add("\t".repeat(slashCount > 2 ? slashCount - 2 : 0) + url);
        }
        try(FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(joiner.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
