package App;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class SongManager {

    private SongRepo hitList;

    public SongManager() {
    }

    public SongRepo rmfmaxxHitList(){
        hitList = new SongRepo();
        try {
            String address = "https://www.rmfmaxxx.pl/top40";
            Document doc = Jsoup.connect(address).get();
            Elements hits = doc.select("div.list-songs a.is-title");
            int counter = 0;
            String[] artistsAndSong = new String[40];
            for(Element element : hits){
                artistsAndSong[counter] = element.attr("title");
                if(counter == 39){
                    break;
                }
                counter++;
            }
            counter = 0;
            for (String s : artistsAndSong) {
                String[] splitArtistsAndName = s.split(" - ");
                hitList.addSong(new Song(splitArtistsAndName[1]));
                String[] splitArtistsUsingSlash = splitArtistsAndName[0].split(" / ");
                for (String string : splitArtistsUsingSlash) {
                    hitList.getSong(counter).addArtist(new Artist(string));
                }
                counter++;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return hitList;
    }

    public SongRepo radiozetHitList(){
        hitList = new SongRepo();
        try {
            String address = "https://www.radiozet.pl/Radio/Lista-przebojow";
            Document doc = Jsoup.connect(address).get();
            Elements hitArtist = doc.select("div.chart__full__list__track-list div.track div.track div.artist-track");
            int counter = 0;
            String[] artists = new String[30];
            for(Element element : hitArtist){
                artists[counter] = element.text();
                if(counter == 29){
                    break;
                }
                counter++;
            }
            counter = 0;
            Elements hitNameAndFeat = doc.select("div.chart__full__list__track-list div.track div.track div.title-track");
            for(Element element : hitNameAndFeat){
                if(counter == 30){
                    break;
                }
                String[] splitNameAndFeat = element.text().split(" \\(feat. ");
                for(int i = 0; i < splitNameAndFeat.length; i++){
                    if(i == 0){
                        hitList.addSong(new Song(splitNameAndFeat[i]));
                        hitList.getSong(counter).addArtist(new Artist(artists[counter]));
                    }else{
                        String[] splitIfMoreThanOneFeat = splitNameAndFeat[i].split(", ");
                        for(int j = 0; j < splitIfMoreThanOneFeat.length; j++){
                            if(j == splitIfMoreThanOneFeat.length-1){
                                String ArtistWithoutParenthesis = splitIfMoreThanOneFeat[j].substring(0, splitIfMoreThanOneFeat[j].length()-1);
                                hitList.getSong(counter).addArtist(new Artist(ArtistWithoutParenthesis));
                            }else{
                                hitList.getSong(counter).addArtist(new Artist(splitIfMoreThanOneFeat[j]));
                            }
                        }
                    }
                }
                counter++;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return hitList;
    }

    public SongRepo rmffmHitList(){
        hitList = new SongRepo();
        try{
            String address = "https://www.rmf.fm/au/?a=poplista";
            Document doc = Jsoup.connect(address).get();
            Elements hits = doc.select("div.box-text div.poplista-artist-title");
            int counter = 0;
            for(Element element : hits){
                if(counter == 20){
                    break;
                }
                String text = element.text();
                String[] splitArtistsAndName =  text.split(": ");
                hitList.addSong(new Song(splitArtistsAndName[1]));
                String[] splitArtistsUsingFeat = splitArtistsAndName[0].split(" feat. ");
                for(String string : splitArtistsUsingFeat){
                    hitList.getSong(counter).addArtist(new Artist(string));
                }
                counter++;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return hitList;
    }

    public SongRepo eskaHitList(){
        hitList = new SongRepo();
        try{
            String address = "https://www.eska.pl/goraca20/";
            Document doc = Jsoup.connect(address).get();
            Elements hitNames = doc.select("div.artist-hits a.single-hit__title");
            int counter = 0;
            for (Element element : hitNames) {
                if(counter == 20){
                    break;
                }
                hitList.addSong(new Song(element.text()));
                counter++;
            }
            counter = 0;
            Elements hitArtists = doc.select("div.artist-hits div.single-hit__info ul");
            for (Element element : hitArtists) {
                if(counter == 20){
                    break;
                }
                Elements artists = element.select("a");
                for(Element element1 : artists){
                    hitList.getSong(counter).addArtist(new Artist(element1.text()));
                }
                counter++;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return  hitList;
    }

    public SongRepo radiowawaHitList(){
        hitList = new SongRepo();
        try{
            String address = "http://www.radiowawa.pl/top13";
            Document doc = Jsoup.connect(address).get();
            Elements hitNames = doc.select("div.notowanie-row span.title");
            int counter = 0;
            for (Element element : hitNames) {
                hitList.addSong(new Song(element.text()));
                if(counter == 13){
                    break;
                }
                counter++;
            }
            Elements hitArtists = doc.select("div.notowanie-row span.artist");
            counter = 0;
            for(Element element : hitArtists){
                if(counter == 13){
                    break;
                }
                String[] split = element.text().split(",");
                for (String s : split) {
                    hitList.getSong(counter).addArtist(new Artist(s));
                }
                counter++;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return hitList;
    }

    public SongRepo voxfmHitList(){
        hitList = new SongRepo();
        try{
            String address = "https://www.voxfm.pl/bestlista/";
            Document doc = Jsoup.connect(address).get();
            Elements hitNames = doc.select("div.artist-hits a.single-hit__title");
            int counter = 0;
            for(Element element : hitNames){
                if(counter == 20){
                    break;
                }
                hitList.addSong(new Song(element.text()));
                counter++;
            }
            counter = 0;
            Elements hitArtists = doc.select("div.artist-hits div.single-hit__info ul");
            for(Element element : hitArtists){
                if(counter == 20){
                    break;
                }
                Elements artists = element.select("a");
                for(Element element1 : artists){
                    hitList.getSong(counter).addArtist(new Artist(element1.text()));
                }
                counter++;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return hitList;
    }
}
