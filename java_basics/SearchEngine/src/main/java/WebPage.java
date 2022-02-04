import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.HashSet;

public class WebPage {
    private String path;
    private Connection.Response response;

    public WebPage(String path) {
        this.path = path;
        query();
    }

    public void query() {
        try {
            response = Jsoup.connect((path)).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    public int getCode() {
        if (response == null) {
            return 0;
        }
        return response.statusCode();
    }

    public String getContent() {
//        if (response == null) {
//            return "";
//        }
        return response.body();
    }

    public HashSet<String> getUrls(){
        HashSet<String> children = new HashSet<>();
        if (response == null) {
            return children;
        }
        Document data = null;
        try {
            data = response.parse();
            for (Element line : data.select("[href~=^/[^/]]")) {
                String relativeLink = line.attr("href");
                relativeLink = relativeLink.split("[?#]")[0];
                if (relativeLink.matches(".*\\.\\w{2,4}$")) continue;
                if (relativeLink.endsWith("/"))
                    relativeLink = relativeLink.substring(0, relativeLink.length() - 1);
                children.add(relativeLink);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return children;
    }
}
