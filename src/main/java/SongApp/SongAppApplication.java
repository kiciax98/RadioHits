package SongApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SongAppApplication {
    public static void main(String[] args){
        SpringApplication.run(SongAppApplication.class, args);
    }

}
