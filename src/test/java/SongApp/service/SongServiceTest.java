package SongApp.service;

import SongApp.model.Radio;
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
        songService.getRadioHits(Radio.RMFMAXX);
        Mockito.verify(songRepository, Mockito.times(1)).getSongs(Radio.RMFMAXX);
    }

    @Test
    public void getRadiozetHitListTest() {
        songService.getRadioHits(Radio.RADIOZET);
        Mockito.verify(songRepository, Mockito.times(1)).getSongs(Radio.RADIOZET);
    }

    @Test
    public void getRmffmHitListTest() {
        songService.getRadioHits(Radio.RMFFM);
        Mockito.verify(songRepository, Mockito.times(1)).getSongs(Radio.RMFFM);
    }

    @Test
    public void getRadiosupernovaHitListTest() {
        songService.getRadioHits(Radio.RADIOSUPERNOVA);
        Mockito.verify(songRepository, Mockito.times(1)).getSongs(Radio.RADIOSUPERNOVA);
    }

    @Test
    public void getEskaHitListTest() {
        songService.getRadioHits(Radio.ESKA);
        Mockito.verify(songRepository, Mockito.times(1)).getSongs(Radio.ESKA);
    }

    @Test
    public void getVoxfmHitListTest() {
        songService.getRadioHits(Radio.VOXFM);
        Mockito.verify(songRepository, Mockito.times(1)).getSongs(Radio.VOXFM);
    }
}
