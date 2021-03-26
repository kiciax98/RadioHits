package SongApp.service.utils;

import SongApp.model.Song;
import SongApp.model.SongArtist;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class RmfmaxxUtils implements Radio{

    public RmfmaxxUtils() {
    }

    @Override
    public List<Song> getSongNameAndArtistsFromElements(Elements hits) {
        List<Song> songList  = new ArrayList<>();
        int counter = 0;
        for (Element element : hits) {
            if(counter == 40){
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
