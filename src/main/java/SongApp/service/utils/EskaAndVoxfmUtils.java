package SongApp.service.utils;

import SongApp.model.Song;
import SongApp.model.SongArtist;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class EskaAndVoxfmUtils implements Radio{

    private final String address;

    public EskaAndVoxfmUtils(final String address) {
        this.address = address;
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
        Elements hitArtists = getSelectedElements(connectToAddress(address),"div.artist-hits div.single-hit__info ul");
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
