package SongApp.service.utils;

import SongApp.repository.SongRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private SongRepository songRepository;

    private RadiosupernovaUtils radiosupernovaUtils;
    private RadiozetUtils radiozetUtils;
    private RmfmaxxUtils rmfmaxxUtils;
    private RmffmUtils rmffmUtils;
    private VoxfmUtils voxfmUtils;
    private EskaUtils eskaUtils;

    public ScheduledTask(SongRepository songRepository, RadiosupernovaUtils radiosupernovaUtils, RadiozetUtils radiozetUtils, RmfmaxxUtils rmfmaxxUtils, RmffmUtils rmffmUtils, VoxfmUtils voxfmUtils, EskaUtils eskaUtils) {
        this.songRepository = songRepository;
        this.radiosupernovaUtils = radiosupernovaUtils;
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
        songRepository.addSongList("radiosupernova", radiosupernovaUtils.getSongList());
        songRepository.addSongList("eska", eskaUtils.getSongList());
        songRepository.addSongList("voxfm", voxfmUtils.getSongList());
    }
}
