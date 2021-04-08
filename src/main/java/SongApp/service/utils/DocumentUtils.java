package SongApp.service.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class DocumentUtils {
    private Document document;
    private Connection connection;

    public void connectToWebsite(String url) {
        connection = Jsoup.connect(url);
    }

    public void getWebsiteData() {
        try {
            document = connection.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Elements getSelectedElements(String cssQuery) {
        return document.select(cssQuery);
    }
}
