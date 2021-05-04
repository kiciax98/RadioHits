package SongApp.model;

import org.junit.Assert;
import org.junit.Test;

public class SongArtistTest {

    @Test
    public void getName() {
        SongArtist songArtist = new SongArtist("Kygo");
        Assert.assertEquals("Kygo", songArtist.getName());
    }
}
