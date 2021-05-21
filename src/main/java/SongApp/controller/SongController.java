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
        return songService.getRadioHits(Radio.RMFMAXX);
    }

    @GetMapping("/radiozet")
    public List<Song> radiozetHitList(){
        return songService.getRadioHits(Radio.RADIOZET);
    }

    @GetMapping("/rmffm")
    public List<Song> rmffmHitList(){
        return songService.getRadioHits(Radio.RMFFM);
    }

    @GetMapping("/eska")
    public List<Song> eskaHitList(){
        return songService.getRadioHits(Radio.ESKA);
    }

    @GetMapping("/radiosupernova")
    public List<Song> radiosupernovaHitList(){
        return songService.getRadioHits(Radio.RADIOSUPERNOVA);
    }

    @GetMapping("/voxfm")
    public List<Song> voxfmHitList(){
        return songService.getRadioHits(Radio.VOXFM);
    }
}
