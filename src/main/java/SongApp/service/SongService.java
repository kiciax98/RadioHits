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

    public List<Song> getRadio(Enum radio) {
        return songRepository.getSongList(radio);
    }

    public void updateRmfmaxx() {
        songRepository.updateSongList(Radio.RMFMAXX, rmfmaxxUtils.getSongList());
    }

    public void updateRadiozet() {
        songRepository.updateSongList(Radio.RADIOZET, radiozetUtils.getSongList());
    }

    public void updateRmffm() {
        songRepository.updateSongList(Radio.RMFFM, rmffmUtils.getSongList());
    }

    public void updateRadiosupernova() {
        songRepository.updateSongList(Radio.RADIOSUPERNOVA, radiosupernovaUtils.getSongList());
    }

    public void updateEska() {
        songRepository.updateSongList(Radio.ESKA, eskaUtils.getSongList());
    }

    public void updateVoxfm() {
        songRepository.updateSongList(Radio.VOXFM, voxfmUtils.getSongList());
    }
}
