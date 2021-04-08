package SongApp.service.utils;

import SongApp.model.Song;
import org.jsoup.select.Elements;

import java.util.List;

public interface Radio {
    List<Song> getSongNameAndArtistsFromElements(Elements hits);

}
