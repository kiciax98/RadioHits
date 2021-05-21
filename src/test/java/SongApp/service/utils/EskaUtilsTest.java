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
public class EskaUtilsTest {

    @Mock
    DocumentUtils documentUtils;

    @InjectMocks
    EskaUtils eskaUtils;

    @Before
    public void init(){
        String cssQuery = "div.artist-hits a.single-hit__title";
        String cssQuerySecond = "div.artist-hits div.single-hit__info ul";
        File input = new File("src/test/resources/eska.html");
        try {
            Document document = Jsoup.parse(input, "UTF-8");
            ReflectionTestUtils.setField(eskaUtils, "cssQuery", cssQuery);
            ReflectionTestUtils.setField(eskaUtils, "address", "https://www.eska.pl/goraca20/");
            Mockito.when(documentUtils.getSelectedElements(cssQuerySecond)).thenReturn(document.select(cssQuerySecond));
            Mockito.when(documentUtils.getSelectedElements(cssQuery)).thenReturn(document.select(cssQuery));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void songNameEquals() {
        List<Song> results = eskaUtils.getSongList();
        Song song = results.get(0);
        String songName = song.getSongName();
        assertEquals("Rasputin", songName);
    }

    @Test
    public void songNameNotEquals(){
        List<Song> results = eskaUtils.getSongList();
        Song song = results.get(1);
        String songName = song.getSongName();
        assertNotEquals("Rasputin", songName);
    }

    @Test
    public void songFirstArtistEquals(){
        List<Song> results = eskaUtils.getSongList();
        Song song = results.get(0);
        List<SongArtist> songArtists = song.getSongArtists();
        SongArtist songArtist = songArtists.get(0);
        String songArtistName = songArtist.getName();
        assertEquals("Majestic", songArtistName);
    }

    @Test
    public void songSecondArtistEquals(){
        List<Song> results = eskaUtils.getSongList();
        Song song = results.get(0);
        List<SongArtist> songArtists = song.getSongArtists();
        SongArtist songArtist = songArtists.get(1);
        String songArtistName = songArtist.getName();
        assertEquals("Boney M.", songArtistName);
    }

    @Test
    public void songFirstArtistNotEquals(){
        List<Song> results = eskaUtils.getSongList();
        Song song = results.get(1);
        List<SongArtist> songArtists = song.getSongArtists();
        SongArtist songArtist = songArtists.get(0);
        String songArtistName = songArtist.getName();
        assertNotEquals("Boney M.", songArtistName);
    }

    @Test
    public void songSecondArtistNotEquals(){
        List<Song> results = eskaUtils.getSongList();
        Song song = results.get(1);
        List<SongArtist> songArtists = song.getSongArtists();
        SongArtist songArtist = songArtists.get(1);
        String songArtistName = songArtist.getName();
        assertNotEquals("Majestic", songArtistName);
    }

    @Test
    public void listSizeIs20(){
        List<Song> results = eskaUtils.getSongList();
        assertEquals(20, results.size());
    }
}
