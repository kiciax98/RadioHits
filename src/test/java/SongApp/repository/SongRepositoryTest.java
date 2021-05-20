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
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Cisza"));
        SongRepository songRepository = new SongRepository();
        songRepository.updateSongList(Radio.VOXFM, songList);
        Assert.assertEquals(songList, songRepository.getSongList(Radio.VOXFM));
    }

    @Test
    public void getSongListTest() {
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Zaprzepaszczone sily"));
        SongRepository songRepository = new SongRepository();
        songRepository.updateSongList(Radio.ESKA, songList);
        Assert.assertEquals(songList, songRepository.getSongList(Radio.ESKA));
    }
}
