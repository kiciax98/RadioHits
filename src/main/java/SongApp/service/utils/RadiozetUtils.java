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
public class RadiozetUtils implements Radio {

    @Autowired
    private DocumentUtils documentUtils;

    @Value("${radiozet.address}")
    private String address;
    @Value("${radiozet.cssQuery}")
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
        String[] artists = new String[30];
        for (Element element : hits) {
            if (counter == 30) {
                break;
            }
            artists[counter] = element.text();
            counter++;
        }
        counter = 0;
        Elements hitNameAndFeat = documentUtils.getSelectedElements("div.chart__full__list__track-list div.track div.track div.title-track");
        for (Element element : hitNameAndFeat) {
            if (counter == 30) {
                break;
            }
            String[] splitNameAndFeat = element.text().split(" \\(feat. ");
            songList.add(new Song(splitNameAndFeat[0]));
            songList.get(counter).addArtist(new SongArtist(artists[counter]));
            if (splitNameAndFeat.length > 1) {
                String[] splitIfMoreThanOneFeat = splitNameAndFeat[1].split(",");
                for (String string : splitIfMoreThanOneFeat) {
                    songList.get(counter).addArtist(new SongArtist(string.replace(")", "")));
                }
            }
            counter++;
        }
        return songList;
    }
}
