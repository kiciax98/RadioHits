package SongApp.repository;

import SongApp.model.Radio;
import SongApp.model.Song;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SongRepositoryTest {

    @Test
    public void addSongListTest() {
        List<Song> songListExpected = new ArrayList<>();
        Song song = new Song("Cisza");
        songListExpected.add(song);
        SongRepository songRepository = new SongRepository();
        songRepository.updateSongList(Radio.VOXFM, songListExpected);
        List<Song> songListActual = songRepository.getSongs(Radio.VOXFM);
        Assert.assertEquals(songListExpected, songListActual);
    }

    @Test
    public void getSongListTest() {
        List<Song> songListExpected = new ArrayList<>();
        Song song = new Song("Zaprzepaszczone sily");
        songListExpected.add(song);
        SongRepository songRepository = new SongRepository();
        songRepository.updateSongList(Radio.ESKA, songListExpected);
        List<Song> songListActual = songRepository.getSongs(Radio.ESKA);
        Assert.assertEquals(songListExpected, songListActual);
    }
}
