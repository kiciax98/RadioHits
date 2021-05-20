package SongApp.controller;

import SongApp.model.Radio;
import SongApp.model.Song;
import SongApp.service.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SongController {

    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/rmfmaxx")
    public List<Song> rmfmaxxHitList(){
        return songService.getRadio(Radio.RMFMAXX);
    }

    @GetMapping("/radiozet")
    public List<Song> radiozetHitList(){
        return songService.getRadio(Radio.RADIOZET);
    }

    @GetMapping("/rmffm")
    public List<Song> rmffmHitList(){
        return songService.getRadio(Radio.RMFFM);
    }

    @GetMapping("/eska")
    public List<Song> eskaHitList(){
        return songService.getRadio(Radio.ESKA);
    }

    @GetMapping("/radiosupernova")
    public List<Song> radiosupernovaHitList(){
        return songService.getRadio(Radio.RADIOSUPERNOVA);
    }

    @GetMapping("/voxfm")
    public List<Song> voxfmHitList(){
        return songService.getRadio(Radio.VOXFM);
    }
}
