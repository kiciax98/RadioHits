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
public class RmfmaxxUtils implements Radio {

    private DocumentUtils documentUtils;

    @Value("${rmfmaxx.address}")
    private String address;
    @Value("${rmfmaxx.cssQuery}")
    private String cssQuery;

    public RmfmaxxUtils(DocumentUtils documentUtils) {
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
            if (counter == 40) {
                break;
            }
            String[] splitArtistsAndName = element.attr("title").split(" - ");
            songList.add(new Song(splitArtistsAndName[1]));
            String[] splitArtistsUsingSlash = splitArtistsAndName[0].split(" / ");
            for (String string : splitArtistsUsingSlash) {
                songList.get(counter).addArtist(new SongArtist(string));
            }
            counter++;

        }
        return songList;
    }
}
