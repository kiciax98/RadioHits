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
public class RmffmUtils implements SongExtractor {

    private DocumentUtils documentUtils;

    @Value("${rmffm.address}")
    private String address;
    @Value("${rmffm.cssQuery}")
    private String cssQuery;

    public RmffmUtils(DocumentUtils documentUtils) {
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
            if (counter == 20) {
                break;
            }
            String text = element.text();
            String[] splitArtistsAndName = text.split(": ");
            Song song = new Song(splitArtistsAndName[1]);
            songList.add(song);
            String[] splitArtistsUsingFeat = splitArtistsAndName[0].split(" feat. ");
            for (String string : splitArtistsUsingFeat) {
                SongArtist songArtist = new SongArtist(string);
                songList.get(counter).addArtist(songArtist);
            }
            counter++;
        }
        return songList;
    }
}
