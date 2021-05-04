package SongApp.controller;

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
        return songService.getRmfmaxxHitList();
    }

    @GetMapping("/radiozet")
    public List<Song> radiozetHitList(){
        return songService.getRadiozetHitList();
    }

    @GetMapping("/rmffm")
    public List<Song> rmffmHitList(){
        return songService.getRmffmHitList();
    }

    @GetMapping("/eska")
    public List<Song> eskaHitList(){
        return songService.getEskaHitList();
    }

    @GetMapping("/radiowawa")
    public List<Song> radiowawaHitList(){
        return songService.getRadiowawaHitList();
    }

    @GetMapping("/voxfm")
    public List<Song> voxfmHitList(){
        return songService.getVoxfmHitList();
    }
}
