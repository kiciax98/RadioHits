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
public class RmfmaxxUtils implements SongExtractor {

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
        return extractSongNameAndArtistsFromElements(elements);
    }

    @Override
    public List<Song> extractSongNameAndArtistsFromElements(Elements hits) {
        List<Song> songList = new ArrayList<>();
        int counter = 0;
        for (Element element : hits) {
            if (counter == 40) {
                break;
            }
            String[] splitArtistsAndName = element.attr("title").split(" - ");
            Song song = new Song(splitArtistsAndName[1]);
            songList.add(song);
            String[] splitArtistsUsingSlash = splitArtistsAndName[0].split(" / ");
            for (String string : splitArtistsUsingSlash) {
                SongArtist songArtist = new SongArtist(string);
                songList.get(counter).addArtist(songArtist);
            }
            counter++;

        }
        return songList;
    }
}
