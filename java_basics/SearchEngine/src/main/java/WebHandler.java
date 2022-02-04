import Model.IndexingState;
import Model.Page;
import Model.Site;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

public class WebHandler {
    private Connection.Response response;
    private String path;
    private int code;
    private String content = "";


    public static void main(String[] args) {
        String url = "http://radiomv.ru/gj1hgjhsdfdgjhg";
        Site site = new Site();
        site.setName("PoliceFM");
        site.setUrl("http://radiomv.ru");
        site.setStatus(IndexingState.FAILED);
        site.setStatus_time(new Date());
        WebHandler webHandler = new WebHandler(url);
        Page page = webHandler.getPage();
        System.out.println(page.getPath());
        System.out.println(page.getCode());
        System.out.println(page.getContent());
        DBHandler.saveToDB(page);
        DBHandler.saveToDB(site);
    }


    public WebHandler(String url) {
        path = url;
        queryUrl(url);
        if (response != null) {
            code = response.statusCode();
            content = response.body();
        }
    }

    public WebHandler(Site site, String internalLink) {
        this(site.getUrl() + internalLink);
    }

    public WebHandler(Site site) {
        this(site.getUrl());
    }



    private void queryUrl(String url){
        try {
            response = Jsoup.connect((url)).execute();
        } catch (IOException e) {
            if (e instanceof HttpStatusException) {
                code = getErrCode((HttpStatusException) e);
            } else {
                e.printStackTrace();
            }
        }
    }

    private int getErrCode(HttpStatusException exception) {
        String message = exception.getMessage();
        int statusCodePosition = message.indexOf("Status=") + 7;
        String code = message.substring(statusCodePosition, statusCodePosition + 3);
        return Integer.parseInt(code);
    }

    public Page getPage(){
        Page page = new Page();
        page.setPath(path);
        page.setCode(code);
        page.setContent(content);
        return  page;
    }

    public HashSet<String> getInternalLinks() {
        HashSet<String> links = new HashSet<>();
        return links;
    }

}
