package SongApp.service;

import SongApp.model.Radio;
import SongApp.model.Song;
import SongApp.repository.SongRepository;
import SongApp.service.utils.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private SongRepository songRepository;

    private RadiosupernovaUtils radiosupernovaUtils;
    private RadiozetUtils radiozetUtils;
    private RmfmaxxUtils rmfmaxxUtils;
    private RmffmUtils rmffmUtils;
    private VoxfmUtils voxfmUtils;
    private EskaUtils eskaUtils;

    public SongService(SongRepository songRepository, RadiosupernovaUtils radiosupernovaUtils, RadiozetUtils radiozetUtils, RmfmaxxUtils rmfmaxxUtils, RmffmUtils rmffmUtils, VoxfmUtils voxfmUtils, EskaUtils eskaUtils) {
        this.songRepository = songRepository;
        this.radiosupernovaUtils = radiosupernovaUtils;
        this.radiozetUtils = radiozetUtils;
        this.rmfmaxxUtils = rmfmaxxUtils;
        this.rmffmUtils = rmffmUtils;
        this.voxfmUtils = voxfmUtils;
        this.eskaUtils = eskaUtils;
    }

    public List<Song> getRadioHits(Radio radio) {
        return songRepository.getSongs(radio);
    }

    public void updateRmfmaxx() {
        List<Song> songList = rmfmaxxUtils.getSongList();
        songRepository.updateSongList(Radio.RMFMAXX, songList);
    }

    public void updateRadiozet() {
        List<Song> songList = radiozetUtils.getSongList();
        songRepository.updateSongList(Radio.RADIOZET, songList);
    }

    public void updateRmffm() {
        List<Song> songList = rmffmUtils.getSongList();
        songRepository.updateSongList(Radio.RMFFM, songList);
    }

    public void updateRadiosupernova() {
        List<Song> songList = radiosupernovaUtils.getSongList();
        songRepository.updateSongList(Radio.RADIOSUPERNOVA, songList);
    }

    public void updateEska() {
        List<Song> songList =eskaUtils.getSongList();
        songRepository.updateSongList(Radio.ESKA, songList);
    }

    public void updateVoxfm() {
        List<Song> songList = voxfmUtils.getSongList();
        songRepository.updateSongList(Radio.VOXFM, songList);
    }
}
