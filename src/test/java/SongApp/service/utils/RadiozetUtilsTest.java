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
public class RadiozetUtilsTest {

    @Mock
    DocumentUtils documentUtils;

    @InjectMocks
    RadiozetUtils radiozetUtils;

    @Before
    public void init(){
        String cssQuery = "div.chart__full__list__track-list div.track div.track div.artist-track";
        String cssQuerySecond = "div.chart__full__list__track-list div.track div.track div.title-track";
        File input = new File("src/test/resources/radiozet.html");
        try {
            Document document = Jsoup.parse(input, "UTF-8");
            ReflectionTestUtils.setField(radiozetUtils, "cssQuery", cssQuery);
            ReflectionTestUtils.setField(radiozetUtils, "address", "https://www.radiozet.pl/Radio/Lista-przebojow");
            Mockito.when(documentUtils.getSelectedElements(cssQuerySecond)).thenReturn(document.select(cssQuerySecond));
            Mockito.when(documentUtils.getSelectedElements(cssQuery)).thenReturn(document.select(cssQuery));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void songNameEquals() {
        List<Song> results = radiozetUtils.getSongList();
        assertEquals("Bez Ciebie", results.get(0).getSongName());
    }

    @Test
    public void songNameNotEquals(){
        List<Song> results = radiozetUtils.getSongList();
        assertNotEquals("Rasputin", results.get(1).getSongName());
    }

    @Test
    public void songFirstArtistEquals(){
        List<Song> results = radiozetUtils.getSongList();
        assertEquals("Dawid Kwiatkowski", results.get(0).getSongArtists().get(0).getName());
    }

    @Test
    public void songSecondArtistEquals(){
        List<Song> results = radiozetUtils.getSongList();
        assertEquals("Topic & A7S", results.get(1).getSongArtists().get(1).getName());
    }

    @Test
    public void songFirstArtistNotEquals(){
        List<Song> results = radiozetUtils.getSongList();
        assertNotEquals("Boney M.", results.get(0).getSongArtists().get(0).getName());
    }

    @Test
    public void songSecondArtistNotEquals(){
        List<Song> results = radiozetUtils.getSongList();
        assertNotEquals("Majestic", results.get(1).getSongArtists().get(1).getName());
    }

    @Test
    public void listSizeIs30(){
        List<Song> results = radiozetUtils.getSongList();
        assertEquals(30, results.size());
    }
}
