package SongApp.service;

import SongApp.model.Song;
import SongApp.repository.SongRepository;
import SongApp.service.utils.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    private RadiowawaUtils radiowawaUtils;
    private RadiozetUtils radiozetUtils;
    private RmfmaxxUtils rmfmaxxUtils;
    private RmffmUtils rmffmUtils;
    private VoxfmUtils voxfmUtils;
    private EskaUtils eskaUtils;

    public SongService(SongRepository songRepository, RadiowawaUtils radiowawaUtils, RadiozetUtils radiozetUtils, RmfmaxxUtils rmfmaxxUtils, RmffmUtils rmffmUtils, VoxfmUtils voxfmUtils, EskaUtils eskaUtils) {
        this.songRepository = songRepository;
        this.radiowawaUtils = radiowawaUtils;
        this.radiozetUtils = radiozetUtils;
        this.rmfmaxxUtils = rmfmaxxUtils;
        this.rmffmUtils = rmffmUtils;
        this.voxfmUtils = voxfmUtils;
        this.eskaUtils = eskaUtils;
    }

    public List<Song> rmfmaxxHitList() {
        songRepository.addSongList("rmfmaxx", rmfmaxxUtils.getSongList());
        return songRepository.getSongList("rmfmaxx");
    }

    public List<Song> radiozetHitList() {
        songRepository.addSongList("radiozet", radiozetUtils.getSongList());
        return songRepository.getSongList("radiozet");
    }

    public List<Song> rmffmHitList() {
        songRepository.addSongList("rmffm", rmffmUtils.getSongList());
        return songRepository.getSongList("rmffm");
    }

    public List<Song> radiowawaHitList() {
        songRepository.addSongList("radiowawa", radiowawaUtils.getSongList());
        return songRepository.getSongList("radiowawa");
    }

    public List<Song> eskaHitList() {
        songRepository.addSongList("eska", eskaUtils.getSongList());
        return songRepository.getSongList("eska");
    }

    public List<Song> voxfmHitList() {
        songRepository.addSongList("voxfm", voxfmUtils.getSongList());
        return songRepository.getSongList("voxfm");
    }
}
