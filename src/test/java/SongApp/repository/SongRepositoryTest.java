package SongApp.repository;

import SongApp.model.Song;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

public class SongRepositoryTest {

    @Test
    public void addSongListTest() {
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Cisza"));
        SongRepository songRepository = new SongRepository();
        songRepository.addSongList("testAdd", songList);
        Assert.assertEquals(songList, songRepository.getSongList("testAdd"));
    }

    @Test
    public void getSongListTest() {
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Zaprzepaszczone sily"));
        SongRepository songRepository = new SongRepository();
        songRepository.addSongList("testGet", songList);
        Assert.assertEquals(songList, songRepository.getSongList("testGet"));
    }
}
