package SongApp.repository;

import SongApp.model.Radio;
import SongApp.model.Song;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SongRepository {

    private Map<Radio, List<Song>> songMap;

    public SongRepository() {
        this.songMap = new HashMap<>();
    }

    public void updateSongList(Radio radio, List<Song> songList){
        this.songMap.put(radio, songList);
    }

    public List<Song> getSongs(Radio radio){
        return songMap.get(radio);
    }

}
