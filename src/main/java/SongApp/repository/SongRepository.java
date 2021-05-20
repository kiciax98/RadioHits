package SongApp.repository;

import SongApp.model.Song;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SongRepository {

    private Map<Enum, List<Song>> songMap;

    public SongRepository() {
        this.songMap = new HashMap<>();
    }

    public void updateSongList(Enum radio, List<Song> songList){
        this.songMap.put(radio, songList);
    }

    public List<Song> getSongList(Enum radio){
        return songMap.get(radio);
    }

}
