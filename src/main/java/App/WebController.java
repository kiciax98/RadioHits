package App;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    private SongManager songManager;

    public WebController(SongManager songManager) {
        this.songManager = new SongManager();
    }

    @GetMapping("/rmfmaxx")
    public SongRepo rmfmaxxHitList(){
        return songManager.rmfmaxxHitList();
    }

    @GetMapping("/radiozet")
    public SongRepo radiozetHitList(){
        return songManager.radiozetHitList();
    }

    @GetMapping("/rmffm")
    public SongRepo rmffmHitList(){
        return songManager.rmffmHitList();
    }

    @GetMapping("/eska")
    public SongRepo eskaHitList(){
        return songManager.eskaHitList();
    }

    @GetMapping("/radiowawa")
    public SongRepo radiowawaHitList(){
        return songManager.radiowawaHitList();
    }

    @GetMapping("/voxfm")
    public SongRepo voxfmHitList(){
        return songManager.voxfmHitList();
    }
}
