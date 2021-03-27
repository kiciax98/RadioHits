package SongApp.service;

import SongApp.model.Song;
import SongApp.repository.SongRepository;
import SongApp.service.utils.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private SongRepository songRepository;

    public SongService() {
        this.songRepository = new SongRepository();
    }

    public List<Song> rmfmaxxHitList() {
        RmfmaxxUtils rmfmaxxUtils = new RmfmaxxUtils();
        String address = "https://www.rmfmaxxx.pl/top40";
        Document document = rmfmaxxUtils.connectToAddress(address);
        String cssQuery = "div.list-songs a.is-title";
        Elements elements = rmfmaxxUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = rmfmaxxUtils.getSongNameAndArtistsFromElements(elements);
        songRepository.addSongList("rmfmaxx", songList);
        return songRepository.getSongList("rmfmaxx");
    }

    public List<Song> radiozetHitList() {
        String address = "https://www.radiozet.pl/Radio/Lista-przebojow";
        RadiozetUtils radiozetUtils = new RadiozetUtils(address);
        Document document = radiozetUtils.connectToAddress(address);
        String cssQuery = "div.chart__full__list__track-list div.track div.track div.artist-track";
        Elements elements = radiozetUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = radiozetUtils.getSongNameAndArtistsFromElements(elements);
        songRepository.addSongList("radiozet", songList);
        return songRepository.getSongList("radiozet");
    }

    public List<Song> rmffmHitList() {
        RmffmUtils rmffmUtils = new RmffmUtils();
        String address = "https://www.rmf.fm/au/?a=poplista";
        Document document = rmffmUtils.connectToAddress(address);
        String cssQuery = "div.box-text div.poplista-artist-title";
        Elements elements = rmffmUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = rmffmUtils.getSongNameAndArtistsFromElements(elements);
        songRepository.addSongList("rmffm", songList);
        return songRepository.getSongList("rmffm");
    }

    public List<Song> eskaHitList() {
        String address = "https://www.eska.pl/goraca20/";
        EskaAndVoxfmUtils eskaAndVoxfmUtils = new EskaAndVoxfmUtils(address);
        Document document = eskaAndVoxfmUtils.connectToAddress(address);
        String cssQuery = "div.artist-hits a.single-hit__title";
        Elements elements = eskaAndVoxfmUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = eskaAndVoxfmUtils.getSongNameAndArtistsFromElements(elements);
        songRepository.addSongList("eska", songList);
        return songRepository.getSongList("eska");
    }

    public List<Song> radiowawaHitList() {
        String address = "http://www.radiowawa.pl/top13";
        RadiowawaUtils radiowawaUtils = new RadiowawaUtils(address);
        Document document = radiowawaUtils.connectToAddress(address);
        String cssQuery = "div.notowanie-row span.title";
        Elements elements = radiowawaUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = radiowawaUtils.getSongNameAndArtistsFromElements(elements);
        songRepository.addSongList("radiowawa", songList);
        return songRepository.getSongList("radiowawa");
    }

    public List<Song> voxfmHitList() {
        String address = "https://www.voxfm.pl/bestlista/";
        EskaAndVoxfmUtils voxfmUtils = new EskaAndVoxfmUtils(address);
        Document document = voxfmUtils.connectToAddress(address);
        String cssQuery = "div.artist-hits a.single-hit__title";
        Elements elements = voxfmUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = voxfmUtils.getSongNameAndArtistsFromElements(elements);
        songRepository.addSongList("voxfm", songList);
        return songRepository.getSongList("voxfm");
    }
}
