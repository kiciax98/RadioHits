package SongApp.service.utils;

import SongApp.model.Song;
import SongApp.model.SongArtist;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RadiowawaUtils implements Radio {

    @Autowired
    private DocumentUtils documentUtils;

    @Value("${radiowawa.address}")
    private String address;
    @Value("${radiowawa.cssQuery}")
    private String cssQuery;

    public List<Song> getSongList() {
        documentUtils.connectToWebsite(address);
        documentUtils.getWebsiteData();
        Elements elements = documentUtils.getSelectedElements(cssQuery);
        List<Song> songList = getSongNameAndArtistsFromElements(elements);
        return songList;
    }

    @Override
    public List<Song> getSongNameAndArtistsFromElements(Elements hits) {
        List<Song> songList = new ArrayList<>();
        int counter = 0;
        for (Element element : hits) {
            songList.add(new Song(element.text()));
            if (counter == 13) {
                break;
            }
            counter++;
        }
        Elements hitArtists = documentUtils.getSelectedElements("div.notowanie-row span.artist");
        counter = 0;
        for (Element element : hitArtists) {
            if (counter == 13) {
                break;
            }
            String[] split = element.text().split(",");
            for (String s : split) {
                songList.get(counter).addArtist(new SongArtist(s));
            }
            counter++;
        }
        return songList;
    }
}
