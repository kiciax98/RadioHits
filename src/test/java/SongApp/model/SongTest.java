package SongApp.model;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class SongTest {

    @Test
    public void getSongName_CorrectName() {
        Song song = new Song("Baby");
        Assert.assertEquals("Baby", song.getSongName());
    }

    @Test
    public void getSongName_WrongName() {
        Song song = new Song("Heaven");
        Assert.assertNotEquals("Baby", song.getSongName());
    }

    @Test
    public void getSongArtists_EmptyList() {
        Song song = new Song("Los cebula i krokodyle lzy");
        Assert.assertEquals(new ArrayList<>(), song.getSongArtists());
    }

    @Test
    public void addArtist() {
        Song song = new Song("Los cebula i krokodyle lzy");
        SongArtist songArtist = new SongArtist("Ludovico Einaudi");
        song.addArtist(songArtist);
        Assert.assertEquals("Ludovico Einaudi", song.getSongArtists().get(0).getName());
    }

}