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
public class RadiosupernovaUtilsTest {

    @Mock
    DocumentUtils documentUtils;

    @InjectMocks
    RadiosupernovaUtils radiosupernovaUtils;

    @Before
    public void init() {
        String cssQuery = "div.artist-hits a.single-hit__title";
        String cssQuerySecond = "div.artist-hits div.single-hit__info ul";
        File input = new File("src/test/resources/radiosupernova.html");
        try {
            Document document = Jsoup.parse(input, "UTF-8");
            ReflectionTestUtils.setField(radiosupernovaUtils, "cssQuery", cssQuery);
            ReflectionTestUtils.setField(radiosupernovaUtils, "address", "https://www.radiosupernova.pl/super-13/");
            Mockito.when(documentUtils.getSelectedElements(cssQuerySecond)).thenReturn(document.select(cssQuerySecond));
            Mockito.when(documentUtils.getSelectedElements(cssQuery)).thenReturn(document.select(cssQuery));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void songNameEquals() {
        List<Song> results = radiosupernovaUtils.getSongList();
        Song song = results.get(0);
        String songName = song.getSongName();
        assertEquals("Szklany sufit", songName);
    }

    @Test
    public void songNameNotEquals(){
        List<Song> results = radiosupernovaUtils.getSongList();
        Song song = results.get(1);
        String songName = song.getSongName();
        assertNotEquals("Szklany sufit", songName);
    }

    @Test
    public void songFirstArtistEquals(){
        List<Song> results = radiosupernovaUtils.getSongList();
        Song song = results.get(0);
        List<SongArtist> songArtists = song.getSongArtists();
        SongArtist songArtist = songArtists.get(0);
        String songArtistName = songArtist.getName();
        assertEquals("Three Of Us", songArtistName);
    }

    @Test
    public void songFirstArtistNotEquals(){
        List<Song> results = radiosupernovaUtils.getSongList();
        Song song = results.get(0);
        List<SongArtist> songArtists = song.getSongArtists();
        SongArtist songArtist = songArtists.get(0);
        String songArtistName = songArtist.getName();
        assertNotEquals("SONIA", songArtistName);
    }

    @Test
    public void songSecondArtistNotEquals(){
        List<Song> results = radiosupernovaUtils.getSongList();
        Song song = results.get(1);
        List<SongArtist> songArtists = song.getSongArtists();
        SongArtist songArtist = songArtists.get(0);
        String songArtistName = songArtist.getName();
        assertNotEquals("Three Of Us", songArtistName);
    }

    @Test
    public void listSizeIs13(){
        List<Song> results = radiosupernovaUtils.getSongList();
        assertEquals(13, results.size());
    }
}
