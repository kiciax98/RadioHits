package SongApp.service;

import SongApp.repository.SongRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SongServiceTest {

    @Mock
    SongRepository songRepository;

    @InjectMocks
    SongService songService;

    @Test
    public void getRmfmaxxHitListTest() {
        songService.getRmfmaxxHitList();
        Mockito.verify(songRepository, Mockito.times(1)).getSongList("rmfmaxx");
    }

    @Test
    public void getRadiozetHitListTest() {
        songService.getRadiozetHitList();
        Mockito.verify(songRepository, Mockito.times(1)).getSongList("radiozet");
    }

    @Test
    public void getRmffmHitListTest() {
        songService.getRmffmHitList();
        Mockito.verify(songRepository, Mockito.times(1)).getSongList("rmffm");
    }

    @Test
    public void getRadiowawaHitListTest() {
        songService.getRadiowawaHitList();
        Mockito.verify(songRepository, Mockito.times(1)).getSongList("radiowawa");
    }

    @Test
    public void getEskaHitListTest() {
        songService.getEskaHitList();
        Mockito.verify(songRepository, Mockito.times(1)).getSongList("eska");
    }

    @Test
    public void getVoxfmHitListTest() {
        songService.getVoxfmHitList();
        Mockito.verify(songRepository, Mockito.times(1)).getSongList("voxfm");
    }
}
