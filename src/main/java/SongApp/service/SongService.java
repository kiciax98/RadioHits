package SongApp.service;

import SongApp.model.Song;
import SongApp.repository.SongRepository;
import SongApp.service.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private RadiowawaUtils radiowawaUtils;
    @Autowired
    private RadiozetUtils radiozetUtils;
    @Autowired
    private RmfmaxxUtils rmfmaxxUtils;
    @Autowired
    private RmffmUtils rmffmUtils;
    @Autowired
    private VoxfmUtils voxfmUtils;
    @Autowired
    private EskaUtils eskaUtils;

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
