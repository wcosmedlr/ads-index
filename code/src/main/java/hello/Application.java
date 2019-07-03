package hello;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.entity.Ad;
import hello.entity.Picture;
import hello.service.AdService;
import hello.service.PictureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("hello.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(AdService adService, PictureService pictureService) {
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Picture>> typeReferenceP = new TypeReference<List<Picture>>(){};
            List<Picture> pictures = null;
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/static/pictures.json");) {
                pictures = mapper.readValue(inputStream,typeReferenceP);
                pictures.forEach(picture -> pictureService.addPicture(picture));
                System.out.println("Pictures Saved!");
            } catch (IOException e){
                System.out.println("Unable to save pictures: " + e.getMessage());
            }

            // read json and write to db
            TypeReference<List<Ad>> typeReference = new TypeReference<List<Ad>>(){};
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/static/ads.json")){
                List<Ad> ads = mapper.readValue(inputStream,typeReference);
                ads.forEach(ad -> {
                    adService.addAd(ad);
                });

                System.out.println("Ads Saved!");
            } catch (IOException e){
                System.out.println("Unable to save ads: " + e.getMessage());
            }


        };
    }

}
