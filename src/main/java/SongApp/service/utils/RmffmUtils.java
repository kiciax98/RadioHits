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
public class RmffmUtils implements Radio {

    @Autowired
    private DocumentUtils documentUtils;

    @Value("${rmffm.address}")
    private String address;
    @Value("${rmffm.cssQuery}")
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
            if (counter == 20) {
                break;
            }
            String text = element.text();
            String[] splitArtistsAndName = text.split(": ");
            songList.add(new Song(splitArtistsAndName[1]));
            String[] splitArtistsUsingFeat = splitArtistsAndName[0].split(" feat. ");
            for (String string : splitArtistsUsingFeat) {
                songList.get(counter).addArtist(new SongArtist(string));
            }
            counter++;
        }
        return songList;
    }
}
