package App;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
public class WebController {

    @GetMapping("/rmfmaxx")
    public ArrayList<String> rmfmaxxHitList(){
        ArrayList<String> hitList = new ArrayList<>();
        try {
            String address = "https://www.rmfmaxxx.pl/top40";
            Document doc = Jsoup.connect(address).get();
            Elements hits = doc.select("div.list-songs a.is-title");
            hits.forEach(element -> hitList.add(element.attr("title")));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return hitList;
    }

    @GetMapping("/radiozet")
    public ArrayList<String> radiozetHitList(){
        ArrayList<String> hitList = new ArrayList<>();
        try {
            String address = "https://www.radiozet.pl/Radio/Lista-przebojow";
            Document doc = Jsoup.connect(address).get();
            Elements hits = doc.select("div.chart__full__list__track-list div.track div.track");
            hits.forEach(element -> hitList.add(element.text()));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return hitList;
    }

    @GetMapping("/rmffm")
    public ArrayList<String> rmffmHitList(){
        ArrayList<String> hitList = new ArrayList<>();
        try{
            String address = "https://www.rmf.fm/au/?a=poplista";
            Document doc = Jsoup.connect(address).get();
            Elements first = doc.select("div#strip-poplista-nr1 div.poplista-artist-title");
            hitList.add(first.text());
            Elements hits = doc.select("div#strip-notowanie div.poplista-artist-title");

            hits.forEach(element -> hitList.add(element.text()));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return hitList;
    }

    @GetMapping("/eska")
    public ArrayList<String> eskaHitList(){
        //until 20 elements
        ArrayList<String> hitList = new ArrayList<>();
        try{
            String address = "https://www.eska.pl/goraca20/";
            Document doc = Jsoup.connect(address).get();
            Elements hits = doc.select("div.artist-hits div.single-hit__info");
            for (Element e : hits) {

            }
            hits.forEach(element -> {
                String str = element.text();
                if(!str.contains("Radio")){
                    if(str.contains("TEKST")){
                        str = str.substring(0, str.length()-6);
                    }
                    hitList.add(str);
                }
            });
            hitList.forEach(s -> System.out.println(s));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return  hitList;
    }

    @GetMapping("/radiowawa")
    public ArrayList<Song> radiowawaHitList(){
        ArrayList<Song> hitList = new ArrayList<>();
        try{
            String address = "http://www.radiowawa.pl/top13";
            Document doc = Jsoup.connect(address).get();
            Elements hitNames = doc.select("div.notowanie-row span.title");
            for (Element element : hitNames) {
                hitList.add(new Song(element.text()));
                if(hitList.size()==13){
                    break;
                }
            }
            Elements hitArtists = doc.select("div.notowanie-row span.artist");
            int counter = 0;
            for(Element element : hitArtists){
                if(counter == 13){
                    break;
                }
                String[] split = element.text().split(",");
                for(int i = 0; i < split.length; i++){
                    hitList.get(counter).addArtist(new Artist(split[i]));
                }
                counter++;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return hitList;
    }

    @GetMapping("/voxfm")
    public ArrayList<Song> voxfmHitList(){
        ArrayList<Song> hitList = new ArrayList<>();
        try{
            String address = "https://www.voxfm.pl/bestlista/";
            Document doc = Jsoup.connect(address).get();
            Elements hitNames = doc.select("div.artist-hits a.single-hit__title");
            for(Element element : hitNames){
                if(hitList.size() == 20){
                    break;
                }
                hitList.add(new Song(element.text()));
            }
            int counter = 0;
            Elements hitArtists = doc.select("div.artist-hits div.single-hit__info ul");
            for(Element element : hitArtists){
                if(counter == 20){
                    break;
                }
                Elements artists = element.select("a");
                for(Element element1 : artists){
                    hitList.get(counter).addArtist(new Artist(element1.text()));
                }
                counter++;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return hitList;
    }
}
