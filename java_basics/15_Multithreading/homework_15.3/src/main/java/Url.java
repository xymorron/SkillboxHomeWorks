import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Url implements Comparable<Url>{
    private String urlName;
    private Url headUrl;
    private Set<Url> visitedUrls;

    public Url(String urlName) {
        this.urlName = urlName;
        headUrl = this;
        visitedUrls = (new ConcurrentHashMap<>()).newKeySet();
    }

    private Url(String urlName, Url headUrl) {
        this.urlName = urlName;
        this.headUrl = headUrl;
    }

    public boolean isVisited() {
        return headUrl.visitedUrls.contains(this);
    }

    public void addToVisited() {
        headUrl.visitedUrls.add(this);
    }

    public HashSet<Url> getChildren() {
        HashSet<Url> children = new HashSet<>();
        if (isVisited()) return children;
        addToVisited();
        try {
            Connection.Response response = Jsoup.connect((urlName)).execute();
            if (response.statusCode() != 200) return children;
            Document data = response.parse();
            for (Element line : data.select("[href~=^/[^/]]")) {
                String relativeLink = line.attr("href");
                relativeLink = relativeLink.split("[?#]")[0];
                if (relativeLink.matches(".*\\.\\w{2,4}$")) continue;
                if (relativeLink.endsWith("/"))
                    relativeLink = relativeLink.substring(0, relativeLink.length() - 1);
                children.add(new Url(headUrl.urlName + relativeLink, headUrl));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return children;
    }

    @Override
    public String toString() {
        return urlName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Url)) return false;
        Url url = (Url) o;
        return urlName.equals(url.urlName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlName);
    }

    @Override
    public int compareTo(Url url) {
        for (int i = 0; i < Math.min(url.urlName.length(), urlName.length()); i++) {
            int diff = urlName.charAt(i) - url.urlName.charAt(i);
            if (diff != 0)
                return diff;
        }
        return urlName.length() - url.urlName.length();
    }

    public String getUrlName() {
        return urlName;
    }
}
