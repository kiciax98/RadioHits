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
        songService.getRadio(Radio.RMFMAXX);
        Mockito.verify(songRepository, Mockito.times(1)).getSongList(Radio.RMFMAXX);
    }

    @Test
    public void getRadiozetHitListTest() {
        songService.getRadio(Radio.RADIOZET);
        Mockito.verify(songRepository, Mockito.times(1)).getSongList(Radio.RADIOZET);
    }

    @Test
    public void getRmffmHitListTest() {
        songService.getRadio(Radio.RMFFM);
        Mockito.verify(songRepository, Mockito.times(1)).getSongList(Radio.RMFFM);
    }

    @Test
    public void getRadiosupernovaHitListTest() {
        songService.getRadio(Radio.RADIOSUPERNOVA);
        Mockito.verify(songRepository, Mockito.times(1)).getSongList(Radio.RADIOSUPERNOVA);
    }

    @Test
    public void getEskaHitListTest() {
        songService.getRadio(Radio.ESKA);
        Mockito.verify(songRepository, Mockito.times(1)).getSongList(Radio.ESKA);
    }

    @Test
    public void getVoxfmHitListTest() {
        songService.getRadio(Radio.VOXFM);
        Mockito.verify(songRepository, Mockito.times(1)).getSongList(Radio.VOXFM);
    }
}
