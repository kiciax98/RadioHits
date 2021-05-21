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
public class RadiosupernovaUtils implements SongExtractor {

    private DocumentUtils documentUtils;

    @Value("${radiosupernova.address}")
    private String address;
    @Value("${radiosupernova.cssQuery}")
    private String cssQuery;

    public RadiosupernovaUtils(DocumentUtils documentUtils) {
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
        List<Song> songList = extractSongName(hits);
        extractSongArtists(songList);
        return songList;
    }

    private void extractSongArtists(List<Song> songList) {
        int counter = 0;
        Elements hitArtists = documentUtils.getSelectedElements("div.artist-hits div.single-hit__info ul");
        for (Element element : hitArtists) {
            if (counter == 13) {
                break;
            }
            Elements artists = element.select("a");
            for (Element element1 : artists) {
                String songArtistName = element1.text();
                SongArtist songArtist = new SongArtist(songArtistName);
                songList.get(counter).addArtist(songArtist);
            }
            counter++;
        }
    }

    private List<Song> extractSongName(Elements hits) {
        List<Song> songList = new ArrayList<>();
        int counter = 0;
        for (Element element : hits) {
            if (counter == 13) {
                break;
            }
            String songName = element.text();
            Song song = new Song(songName);
            songList.add(song);
            counter++;
        }
        return songList;
    }
}
