package SongApp.service.utils;

import SongApp.model.Song;
import SongApp.model.SongArtist;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class RmffmUtils implements Radio{

    public RmffmUtils() {
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
