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
public class RmffmUtilsTest {

    @Mock
    DocumentUtils documentUtils;

    @InjectMocks
    RmffmUtils rmffmUtils;

    @Before
    public void init(){
        String cssQuery = "div.box-text div.poplista-artist-title";
        File input = new File("src/test/resources/rmffm.html");
        try {
            Document document = Jsoup.parse(input, "UTF-8");
            ReflectionTestUtils.setField(rmffmUtils, "cssQuery", cssQuery);
            ReflectionTestUtils.setField(rmffmUtils, "address", "https://www.rmf.fm/au/?a=poplista");
            Mockito.when(documentUtils.getSelectedElements(cssQuery)).thenReturn(document.select(cssQuery));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void songNameEquals() {
        List<Song> results = rmffmUtils.getSongList();
        assertEquals("Love Again", results.get(0).getSongName());
    }

    @Test
    public void songNameNotEquals(){
        List<Song> results = rmffmUtils.getSongList();
        assertNotEquals("Rasputin", results.get(0).getSongName());
    }

    @Test
    public void songFirstArtistEquals(){
        List<Song> results = rmffmUtils.getSongList();
        assertEquals("Alok & Vize", results.get(0).getSongArtists().get(0).getName());
    }

    @Test
    public void songSecondArtistEquals(){
        List<Song> results = rmffmUtils.getSongList();
        assertEquals("Alida", results.get(0).getSongArtists().get(1).getName());
    }

    @Test
    public void songFirstArtistNotEquals(){
        List<Song> results = rmffmUtils.getSongList();
        assertNotEquals("Boney M.", results.get(0).getSongArtists().get(0).getName());
    }

    @Test
    public void songSecondArtistNotEquals(){
        List<Song> results = rmffmUtils.getSongList();
        assertNotEquals("Majestic", results.get(0).getSongArtists().get(1).getName());
    }

    @Test
    public void listSizeIs20(){
        List<Song> results = rmffmUtils.getSongList();
        assertEquals(20, results.size());
    }
}
