package SongApp.service.utils;

import SongApp.repository.SongRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private SongRepository songRepository;

    private RadiowawaUtils radiowawaUtils;
    private RadiozetUtils radiozetUtils;
    private RmfmaxxUtils rmfmaxxUtils;
    private RmffmUtils rmffmUtils;
    private VoxfmUtils voxfmUtils;
    private EskaUtils eskaUtils;

    public ScheduledTask(SongRepository songRepository, RadiowawaUtils radiowawaUtils, RadiozetUtils radiozetUtils, RmfmaxxUtils rmfmaxxUtils, RmffmUtils rmffmUtils, VoxfmUtils voxfmUtils, EskaUtils eskaUtils) {
        this.songRepository = songRepository;
        this.radiowawaUtils = radiowawaUtils;
        this.radiozetUtils = radiozetUtils;
        this.rmfmaxxUtils = rmfmaxxUtils;
        this.rmffmUtils = rmffmUtils;
        this.voxfmUtils = voxfmUtils;
        this.eskaUtils = eskaUtils;
    }

    @Scheduled(fixedDelay = 1000)
    public void addAllHitListsToRepository(){
        songRepository.addSongList("rmfmaxx", rmfmaxxUtils.getSongList());
        songRepository.addSongList("radiozet", radiozetUtils.getSongList());
        songRepository.addSongList("rmffm", rmffmUtils.getSongList());
        songRepository.addSongList("radiowawa", radiowawaUtils.getSongList());
        songRepository.addSongList("eska", eskaUtils.getSongList());
        songRepository.addSongList("voxfm", voxfmUtils.getSongList());
    }
}
