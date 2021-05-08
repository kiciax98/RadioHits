package SongApp.service.utils;

import SongApp.model.Song;
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
public class VoxfmUtilsTest {

    @Mock
    DocumentUtils documentUtils;

    @InjectMocks
    VoxfmUtils voxfmUtils;

    @Before
    public void init(){
        String cssQuery = "div.artist-hits a.single-hit__title";
        String cssQuerySecond = "div.artist-hits div.single-hit__info ul";
        File input = new File("src/test/resources/voxfm.html");
        try {
            Document document = Jsoup.parse(input, "UTF-8");
            ReflectionTestUtils.setField(voxfmUtils, "cssQuery", cssQuery);
            ReflectionTestUtils.setField(voxfmUtils, "address", "https://www.voxfm.pl/bestlista/");
            Mockito.when(documentUtils.getSelectedElements(cssQuerySecond)).thenReturn(document.select(cssQuerySecond));
            Mockito.when(documentUtils.getSelectedElements(cssQuery)).thenReturn(document.select(cssQuery));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void songNameEquals() {
        List<Song> results = voxfmUtils.getSongList();
        assertEquals("Get Out My Head", results.get(0).getSongName());
    }

    @Test
    public void songNameNotEquals(){
        List<Song> results = voxfmUtils.getSongList();
        assertNotEquals("Rasputin", results.get(1).getSongName());
    }

    @Test
    public void songFirstArtistEquals(){
        List<Song> results = voxfmUtils.getSongList();
        assertEquals("Shane Codd", results.get(0).getSongArtists().get(0).getName());
    }

    @Test
    public void songSecondArtistEquals(){
        List<Song> results = voxfmUtils.getSongList();
        assertEquals("Adam Levine", results.get(1).getSongArtists().get(1).getName());
    }

    @Test
    public void songFirstArtistNotEquals(){
        List<Song> results = voxfmUtils.getSongList();
        assertNotEquals("Boney M.", results.get(1).getSongArtists().get(0).getName());
    }

    @Test
    public void songSecondArtistNotEquals(){
        List<Song> results = voxfmUtils.getSongList();
        assertNotEquals("Majestic", results.get(1).getSongArtists().get(1).getName());
    }

    @Test
    public void listSizeIs20(){
        List<Song> results = voxfmUtils.getSongList();
        assertEquals(20, results.size());
    }
}