import org.jsoup.Jsoup;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


public class Main {
    private static String IMAGE_DESTINATION_FOLDER = "data";
    private static String PAGE_ADDRESS = "https://lenta.ru";
    public static void main(String[] args) throws IOException {
        Jsoup.connect(PAGE_ADDRESS).get().select("img").stream()
                .map(img -> img.attr("abs:src"))
                .forEach(imgUrl ->{
                    downloadImage(imgUrl);
                    System.out.println(imgUrl);
                });
    }

    private static void downloadImage(String strImageURL){
        String strImageName =
                strImageURL.substring( strImageURL.lastIndexOf("/") + 1);
        if (strImageName.contains("?"))
                strImageName = strImageName.substring(0, strImageName.indexOf('?'));
        System.out.println(strImageName);
        try {
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();
            byte[] buffer = new byte[4096];
            int n = -1;
            OutputStream os =
                    new FileOutputStream( IMAGE_DESTINATION_FOLDER + "/" + strImageName );
            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
