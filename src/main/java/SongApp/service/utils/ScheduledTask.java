package SongApp.service.utils;

import SongApp.service.SongService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private SongService songService;

    public ScheduledTask(SongService songService) {
        this.songService = songService;
    }

    @Scheduled(fixedRateString = "3600000")
    public void addAllHitListsToRepository(){
        songService.updateRmfmaxx();
        songService.updateRadiozet();
        songService.updateRmffm();
        songService.updateRadiosupernova();
        songService.updateEska();
        songService.updateVoxfm();
    }
}
