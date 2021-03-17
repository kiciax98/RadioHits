package App;

import java.util.ArrayList;

public class Song {
    private String songName;
    private ArrayList<Artist> artists;

    public Song(String songName) {
        this.songName = songName;
        artists = new ArrayList<>();
    }

    public Song(String songName, ArrayList<Artist> artists) {
        this.songName = songName;
        this.artists = artists;
    }

    public String getSongName() {
        return songName;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    /*public String getArtistsAsString(){
        StringBuilder sb = new StringBuilder();
        for(Artist artist : artists){
            sb.append(artist.getName());
            sb.append(" ");
        }
        return sb.toString();
    }*/

    public void addArtist(Artist artist){
        artists.add(artist);
    }
}
