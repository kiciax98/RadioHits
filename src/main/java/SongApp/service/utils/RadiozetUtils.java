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
public class RadiozetUtils implements SongExtractor {

    private DocumentUtils documentUtils;

    @Value("${radiozet.address}")
    private String address;
    @Value("${radiozet.cssQuery}")
    private String cssQuery;

    public RadiozetUtils(DocumentUtils documentUtils) {
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
        String[] artists = extractSongName(hits);
        List<Song> songList = extractSongArtists(artists);
        return  songList;
    }

    private List<Song> extractSongArtists(String[] artists) {
        List<Song> songList = new ArrayList<>();
        int counter = 0;
        Elements hitNameAndFeat = documentUtils.getSelectedElements("div.chart__full__list__track-list div.track div.track div.title-track");
        for (Element element : hitNameAndFeat) {
            if (counter == 30) {
                break;
            }
            String songNameAndFeat = element.text();
            String[] splitNameAndFeat = songNameAndFeat.split(" \\(feat. ");
            Song song = new Song(splitNameAndFeat[0]);
            songList.add(song);
            SongArtist songArtist = new SongArtist(artists[counter]);
            songList.get(counter).addArtist(songArtist);
            if (splitNameAndFeat.length > 1) {
                String[] splitIfMoreThanOneFeat = splitNameAndFeat[1].split(",");
                for (String string : splitIfMoreThanOneFeat) {
                    String songNextArtist = string.replace(")", "");
                    SongArtist songArtist1 = new SongArtist(songNextArtist);
                    songList.get(counter).addArtist(songArtist1);
                }
            }
            counter++;
        }
        return songList;
    }

    private String[] extractSongName(Elements hits) {
        String[] artists = new String[30];
        int counter = 0;
        for (Element element : hits) {
            if (counter == 30) {
                break;
            }
            artists[counter] = element.text();
            counter++;
        }
        return artists;
    }
}
