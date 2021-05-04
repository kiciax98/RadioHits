package SongApp.repository;

import SongApp.model.Song;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SongRepository {

    private Map<String, List<Song>> songMap;

    public SongRepository() {
        this.songMap = new HashMap<>();
    }

    public void addSongList(String radioName, List<Song> songList){
        this.songMap.put(radioName, songList);
    }

    public List<Song> getSongList(String radioName){
        return songMap.get(radioName);
    }

}
