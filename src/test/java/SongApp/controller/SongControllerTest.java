package SongApp.controller;

import SongApp.model.Radio;
import SongApp.service.SongService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SongControllerTest {

    @Mock
    SongService songService;

    @InjectMocks
    SongController songController;

    @Test
    public void getRmfmaxxHitListTest(){
        songController.rmfmaxxHitList();
        Mockito.verify(songService, Mockito.times(1)).getRadio(Radio.RMFMAXX);
    }

    @Test
    public void getRadiozetHitListTest(){
        songController.radiozetHitList();
        Mockito.verify(songService, Mockito.times(1)).getRadio(Radio.RADIOZET);
    }

    @Test
    public void getRmffmHitListTest(){
        songController.rmffmHitList();
        Mockito.verify(songService, Mockito.times(1)).getRadio(Radio.RMFFM);
    }

    @Test
    public void getRadiowawaHitListTest(){
        songController.radiosupernovaHitList();
        Mockito.verify(songService, Mockito.times(1)).getRadio(Radio.RADIOSUPERNOVA);
    }

    @Test
    public void getEskaHitListTest(){
        songController.eskaHitList();
        Mockito.verify(songService, Mockito.times(1)).getRadio(Radio.ESKA);
    }

    @Test
    public void getVoxHitListTest(){
        songController.voxfmHitList();
        Mockito.verify(songService, Mockito.times(1)).getRadio(Radio.VOXFM);
    }
}
