package SongApp.service;

import SongApp.model.Song;
import SongApp.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getRmfmaxxHitList() {
        return songRepository.getSongList("rmfmaxx");
    }

    public List<Song> getRadiozetHitList() {
        return songRepository.getSongList("radiozet");
    }

    public List<Song> getRmffmHitList() {
        return songRepository.getSongList("rmffm");
    }

    public List<Song> getRadiowawaHitList() {
        return songRepository.getSongList("radiowawa");
    }

    public List<Song> getEskaHitList() {
        return songRepository.getSongList("eska");
    }

    public List<Song> getVoxfmHitList() {
        return songRepository.getSongList("voxfm");
    }
}
