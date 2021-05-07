package SongApp.model;

import java.util.ArrayList;
import java.util.List;

public class Song {
    private String songName;
    private List<SongArtist> songArtists;

    public Song(String songName) {
        this.songName = songName;
        this.songArtists = new ArrayList<>();
    }

    public String getSongName() {
        return songName;
    }

    public List<SongArtist> getSongArtists() {
        return songArtists;
    }

    public void addArtist(SongArtist songArtist) {
        songArtists.add(songArtist);
    }
}
