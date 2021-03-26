package SongApp.service.utils;

import SongApp.model.Song;
import SongApp.model.SongArtist;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class RadiowawaUtils implements Radio{

    private final String address;

    public RadiowawaUtils(final String address) {
        this.address = address;
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
        Elements hitArtists = getSelectedElements(connectToAddress(address),"div.notowanie-row span.artist");
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
