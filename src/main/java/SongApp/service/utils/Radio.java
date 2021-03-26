package SongApp.service.utils;

import SongApp.model.Song;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

public interface Radio {

    default Document connectToAddress(String address){
        Document doc = null;
        try {
            doc = Jsoup.connect(address).get();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return doc;
    }

    default Elements getSelectedElements(Document document, String cssQuery){
        return document.select(cssQuery);
    }

    List<Song> getSongNameAndArtistsFromElements(Elements hits);

}
