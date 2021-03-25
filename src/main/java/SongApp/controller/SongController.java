package SongApp.controller;

import SongApp.model.Song;
import SongApp.service.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SongController {

    private SongService songService;

    public SongController() {
        this.songService = new SongService();
    }

    @GetMapping("/rmfmaxx")
    public List<Song> rmfmaxxHitList(){
        return songService.rmfmaxxHitList();
    }

    @GetMapping("/radiozet")
    public List<Song> radiozetHitList(){
        return songService.radiozetHitList();
    }

    @GetMapping("/rmffm")
    public List<Song> rmffmHitList(){
        return songService.rmffmHitList();
    }

    @GetMapping("/eska")
    public List<Song> eskaHitList(){
        return songService.eskaHitList();
    }

    @GetMapping("/radiowawa")
    public List<Song> radiowawaHitList(){
        return songService.radiowawaHitList();
    }

    @GetMapping("/voxfm")
    public List<Song> voxfmHitList(){
        return songService.voxfmHitList();
    }
}
