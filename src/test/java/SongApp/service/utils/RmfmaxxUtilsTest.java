package SongApp.service.utils;

import SongApp.model.Song;
import SongApp.model.SongArtist;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class RmfmaxxUtilsTest {

    @Mock
    DocumentUtils documentUtils;

    @InjectMocks
    RmfmaxxUtils rmfmaxxUtils;

    @Before
    public void init(){
        String cssQuery = "div.list-songs a.is-title";
        File input = new File("src/test/resources/rmfmaxx.html");
        try {
            Document document = Jsoup.parse(input, "UTF-8");
            ReflectionTestUtils.setField(rmfmaxxUtils, "cssQuery", cssQuery);
            ReflectionTestUtils.setField(rmfmaxxUtils, "address", "https://www.rmfmaxxx.pl/top40");
            Mockito.when(documentUtils.getSelectedElements(cssQuery)).thenReturn(document.select(cssQuery));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void songNameEquals() {
        List<Song> results = rmfmaxxUtils.getSongList();
        Song song = results.get(0);
        String songName = song.getSongName();
        assertEquals("Head & Heart", songName);
    }

    @Test
    public void songNameNotEquals(){
        List<Song> results = rmfmaxxUtils.getSongList();
        Song song = results.get(1);
        String songName = song.getSongName();
        assertNotEquals("Rasputin", songName);
    }

    @Test
    public void songFirstArtistEquals(){
        List<Song> results = rmfmaxxUtils.getSongList();
        Song song = results.get(0);
        List<SongArtist> songArtists = song.getSongArtists();
        SongArtist songArtist = songArtists.get(0);
        String songArtistName = songArtist.getName();
        assertEquals("MNEK", songArtistName);
    }

    @Test
    public void songSecondArtistEquals(){
        List<Song> results = rmfmaxxUtils.getSongList();
        Song song = results.get(0);
        List<SongArtist> songArtists = song.getSongArtists();
        SongArtist songArtist = songArtists.get(1);
        String songArtistName = songArtist.getName();
        assertEquals("Joel Corry", songArtistName);
    }

    @Test
    public void songFirstArtistNotEquals(){
        List<Song> results = rmfmaxxUtils.getSongList();
        Song song = results.get(2);
        List<SongArtist> songArtists = song.getSongArtists();
        SongArtist songArtist = songArtists.get(0);
        String songArtistName = songArtist.getName();
        assertNotEquals("MNEK", songArtistName);
    }

    @Test
    public void songSecondArtistNotEquals(){
        List<Song> results = rmfmaxxUtils.getSongList();
        Song song = results.get(2);
        List<SongArtist> songArtists = song.getSongArtists();
        SongArtist songArtist = songArtists.get(1);
        String songArtistName = songArtist.getName();
        assertNotEquals("Joel Corry", songArtistName);
    }

    @Test
    public void listSizeIs40(){
        List<Song> results = rmfmaxxUtils.getSongList();
        assertEquals(40, results.size());
    }
}
