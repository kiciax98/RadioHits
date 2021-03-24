package SongApp.service;

import SongApp.model.Song;
import SongApp.model.SongArtist;
import SongApp.repository.SongRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    private SongRepository songListMap;

    public SongService() {
        this.songListMap = new SongRepository();
    }

    public List<Song> rmfmaxxHitList() {
        try {
            List<Song> songList = new ArrayList<>();
            String address = "https://www.rmfmaxxx.pl/top40";
            Document doc = Jsoup.connect(address).get();
            Elements hits = doc.select("div.list-songs a.is-title");
            int counter = 0;
            String[] artistsAndSong = new String[40];
            for (Element element : hits) {
                artistsAndSong[counter] = element.attr("title");
                if (counter == 39) {
                    break;
                }
                counter++;
            }
            counter = 0;
            for (String s : artistsAndSong) {
                String[] splitArtistsAndName = s.split(" - ");
                songList.add(new Song(splitArtistsAndName[1]));
                String[] splitArtistsUsingSlash = splitArtistsAndName[0].split(" / ");
                for (String string : splitArtistsUsingSlash) {
                    songList.get(counter).addArtist(new SongArtist(string));
                }
                counter++;

            }
            songListMap.addSongList("rmfmaxx", songList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return songListMap.getSongList("rmfmaxx");
    }

    public List<Song> radiozetHitList() {
        try {
            List<Song> songList = new ArrayList<>();
            String address = "https://www.radiozet.pl/Radio/Lista-przebojow";
            Document doc = Jsoup.connect(address).get();
            Elements hitArtist = doc.select("div.chart__full__list__track-list div.track div.track div.artist-track");
            int counter = 0;
            String[] artists = new String[30];
            for (Element element : hitArtist) {
                artists[counter] = element.text();
                if (counter == 29) {
                    break;
                }
                counter++;
            }
            counter = 0;
            Elements hitNameAndFeat = doc.select("div.chart__full__list__track-list div.track div.track div.title-track");
            for (Element element : hitNameAndFeat) {
                if (counter == 30) {
                    break;
                }
                String[] splitNameAndFeat = element.text().split(" \\(feat. ");
                for (int i = 0; i < splitNameAndFeat.length; i++) {
                    if (i == 0) {
                        songList.add(new Song(splitNameAndFeat[i]));
                        songList.get(counter).addArtist(new SongArtist(artists[counter]));
                    } else {
                        String[] splitIfMoreThanOneFeat = splitNameAndFeat[i].split(", ");
                        for (int j = 0; j < splitIfMoreThanOneFeat.length; j++) {
                            if (j == splitIfMoreThanOneFeat.length - 1) {
                                String ArtistWithoutParenthesis = splitIfMoreThanOneFeat[j].substring(0, splitIfMoreThanOneFeat[j].length() - 1);
                                songList.get(counter).addArtist(new SongArtist(ArtistWithoutParenthesis));
                            } else {
                                songList.get(counter).addArtist(new SongArtist(splitIfMoreThanOneFeat[j]));
                            }
                        }
                    }
                }
                counter++;
            }
            songListMap.addSongList("radiozet", songList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return songListMap.getSongList("radiozet");
    }

    public List<Song> rmffmHitList() {
        try {
            List<Song> songList = new ArrayList<>();
            String address = "https://www.rmf.fm/au/?a=poplista";
            Document doc = Jsoup.connect(address).get();
            Elements hits = doc.select("div.box-text div.poplista-artist-title");
            int counter = 0;
            for (Element element : hits) {
                if (counter == 20) {
                    break;
                }
                String text = element.text();
                String[] splitArtistsAndName = text.split(": ");
                songList.add(new Song(splitArtistsAndName[1]));
                String[] splitArtistsUsingFeat = splitArtistsAndName[0].split(" feat. ");
                for (String string : splitArtistsUsingFeat) {
                    songList.get(counter).addArtist(new SongArtist(string));
                }
                counter++;
            }
            songListMap.addSongList("rmffm", songList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return songListMap.getSongList("rmffm");
    }

    public List<Song> eskaHitList() {
        try {
            List<Song> songList = new ArrayList<>();
            String address = "https://www.eska.pl/goraca20/";
            Document doc = Jsoup.connect(address).get();
            Elements hitNames = doc.select("div.artist-hits a.single-hit__title");
            int counter = 0;
            for (Element element : hitNames) {
                if (counter == 20) {
                    break;
                }
                songList.add(new Song(element.text()));
                counter++;
            }
            counter = 0;
            Elements hitArtists = doc.select("div.artist-hits div.single-hit__info ul");
            for (Element element : hitArtists) {
                if (counter == 20) {
                    break;
                }
                Elements artists = element.select("a");
                for (Element element1 : artists) {
                    songList.get(counter).addArtist(new SongArtist(element1.text()));
                }
                counter++;
            }
            songListMap.addSongList("eska", songList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return songListMap.getSongList("eska");
    }

    public List<Song> radiowawaHitList() {
        try {
            List<Song> songList = new ArrayList<>();
            String address = "http://www.radiowawa.pl/top13";
            Document doc = Jsoup.connect(address).get();
            Elements hitNames = doc.select("div.notowanie-row span.title");
            int counter = 0;
            for (Element element : hitNames) {
                songList.add(new Song(element.text()));
                if (counter == 13) {
                    break;
                }
                counter++;
            }
            Elements hitArtists = doc.select("div.notowanie-row span.artist");
            counter = 0;
            for (Element element : hitArtists) {
                if (counter == 13) {
                    break;
                }
                String[] split = element.text().split(",");
                for (String s : split) {
                    songList.get(counter).addArtist(new SongArtist(s));
                }
                counter++;
            }
            songListMap.addSongList("radiowawa", songList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return songListMap.getSongList("radiowawa");
    }

    public List<Song> voxfmHitList() {
        try {
            List<Song> songList = new ArrayList<>();
            String address = "https://www.voxfm.pl/bestlista/";
            Document doc = Jsoup.connect(address).get();
            Elements hitNames = doc.select("div.artist-hits a.single-hit__title");
            int counter = 0;
            for (Element element : hitNames) {
                if (counter == 20) {
                    break;
                }
                songList.add(new Song(element.text()));
                counter++;
            }
            counter = 0;
            Elements hitArtists = doc.select("div.artist-hits div.single-hit__info ul");
            for (Element element : hitArtists) {
                if (counter == 20) {
                    break;
                }
                Elements artists = element.select("a");
                for (Element element1 : artists) {
                    songList.get(counter).addArtist(new SongArtist(element1.text()));
                }
                counter++;
            }
            songListMap.addSongList("voxfm", songList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return songListMap.getSongList("voxfm");
    }
}
