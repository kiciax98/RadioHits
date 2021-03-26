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

    private SongRepository songListMap;

    public SongService() {
        this.songListMap = new SongRepository();
    }

    public List<Song> rmfmaxxHitList() {
        RmfmaxxUtils rmfmaxxUtils = new RmfmaxxUtils();
        String address = "https://www.rmfmaxxx.pl/top40";
        Document document = rmfmaxxUtils.connectToAddress(address);
        String cssQuery = "div.list-songs a.is-title";
        Elements elements = rmfmaxxUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = rmfmaxxUtils.getSongNameAndArtistsFromElements(elements);
        songListMap.addSongList("rmfmaxx", songList);
        return songListMap.getSongList("rmfmaxx");
    }

    public List<Song> radiozetHitList() {
        String address = "https://www.radiozet.pl/Radio/Lista-przebojow";
        RadiozetUtils radiozetUtils = new RadiozetUtils(address);
        Document document = radiozetUtils.connectToAddress(address);
        String cssQuery = "div.chart__full__list__track-list div.track div.track div.artist-track";
        Elements elements = radiozetUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = radiozetUtils.getSongNameAndArtistsFromElements(elements);
        songListMap.addSongList("radiozet", songList);
        return songListMap.getSongList("radiozet");
    }

    public List<Song> rmffmHitList() {
        RmffmUtils rmffmUtils = new RmffmUtils();
        String address = "https://www.rmf.fm/au/?a=poplista";
        Document document = rmffmUtils.connectToAddress(address);
        String cssQuery = "div.box-text div.poplista-artist-title";
        Elements elements = rmffmUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = rmffmUtils.getSongNameAndArtistsFromElements(elements);
        songListMap.addSongList("rmffm", songList);
        return songListMap.getSongList("rmffm");
    }

    public List<Song> eskaHitList() {
        String address = "https://www.eska.pl/goraca20/";
        EskaAndVoxfmUtils eskaAndVoxfmUtils = new EskaAndVoxfmUtils(address);
        Document document = eskaAndVoxfmUtils.connectToAddress(address);
        String cssQuery = "div.artist-hits a.single-hit__title";
        Elements elements = eskaAndVoxfmUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = eskaAndVoxfmUtils.getSongNameAndArtistsFromElements(elements);
        songListMap.addSongList("eska", songList);
        return songListMap.getSongList("eska");
    }

    public List<Song> radiowawaHitList() {
        String address = "http://www.radiowawa.pl/top13";
        RadiowawaUtils radiowawaUtils = new RadiowawaUtils(address);
        Document document = radiowawaUtils.connectToAddress(address);
        String cssQuery = "div.notowanie-row span.title";
        Elements elements = radiowawaUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = radiowawaUtils.getSongNameAndArtistsFromElements(elements);
        songListMap.addSongList("radiowawa", songList);
        return songListMap.getSongList("radiowawa");
    }

    public List<Song> voxfmHitList() {
        String address = "https://www.voxfm.pl/bestlista/";
        EskaAndVoxfmUtils voxfmUtils = new EskaAndVoxfmUtils(address);
        Document document = voxfmUtils.connectToAddress(address);
        String cssQuery = "div.artist-hits a.single-hit__title";
        Elements elements = voxfmUtils.getSelectedElements(document, cssQuery);
        List<Song> songList = voxfmUtils.getSongNameAndArtistsFromElements(elements);
        songListMap.addSongList("voxfm", songList);
        return songListMap.getSongList("voxfm");
    }
}
