package App;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class SongRepo {

    public ArrayList<Song> songHits;

    public SongRepo() {
        this.songHits = new ArrayList<>();
    }

    public void addSong(Song song){
        songHits.add(song);
    }

    public Song getSong(int id){
        return songHits.get(id);
    }
}
