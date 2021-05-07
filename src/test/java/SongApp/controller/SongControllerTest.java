package SongApp.controller;

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
        Mockito.verify(songService, Mockito.times(1)).getRmfmaxxHitList();
    }

    @Test
    public void getRadiozetHitListTest(){
        songController.radiozetHitList();
        Mockito.verify(songService, Mockito.times(1)).getRadiozetHitList();
    }

    @Test
    public void getRmffmHitListTest(){
        songController.rmffmHitList();
        Mockito.verify(songService, Mockito.times(1)).getRmffmHitList();
    }

    @Test
    public void getRadiowawaHitListTest(){
        songController.radiosupernovaHitList();
        Mockito.verify(songService, Mockito.times(1)).getRadiosupernovaHitList();
    }

    @Test
    public void getEskaHitListTest(){
        songController.eskaHitList();
        Mockito.verify(songService, Mockito.times(1)).getEskaHitList();
    }

    @Test
    public void getVoxHitListTest(){
        songController.voxfmHitList();
        Mockito.verify(songService, Mockito.times(1)).getVoxfmHitList();
    }
}
