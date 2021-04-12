package SongApp.service.utils;

import SongApp.model.Song;
import SongApp.model.SongArtist;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EskaUtils implements Radio {

    private DocumentUtils documentUtils;

    @Value("${eska.cssQuery}")
    private String cssQuery;
    @Value("${eska.address}")
    private String address;

    public EskaUtils(DocumentUtils documentUtils) {
        this.documentUtils = documentUtils;
    }

    public List<Song> getSongList() {
        documentUtils.connectToWebsite(address);
        documentUtils.getWebsiteData();
        Elements elements = documentUtils.getSelectedElements(cssQuery);
        return getSongNameAndArtistsFromElements(elements);
    }

    @Override
    public List<Song> getSongNameAndArtistsFromElements(Elements hits) {
        List<Song> songList = new ArrayList<>();
        int counter = 0;
        for (Element element : hits) {
            if (counter == 20) {
                break;
            }
            songList.add(new Song(element.text()));
            counter++;
        }
        counter = 0;
        Elements hitArtists = documentUtils.getSelectedElements("div.artist-hits div.single-hit__info ul");
        for (Element element : hitArtists) {
            if (counter == 20) {
                break;
            }
            Elements artists = element.select("a");
            for (Element element1 : artists) {
                songList.get(counter).addArtist(new SongArtist(element1.text()));
            }
            counter++;
        }
        return songList;
    }
}
