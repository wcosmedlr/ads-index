package hello.util;

import hello.entity.Ad;

import java.util.Arrays;
import java.util.List;

public class RequerimentDescriptionLike extends Requeriment {

    @Override
    public int process(Ad ad) {
        int result = 0;
        String[] words =  ad.getDescription().split("\\s+");
        List<String> objetiveWords = Arrays.asList("Luminoso","Nuevo","Centrico", "Reformado", "Ático");
        if (Arrays.asList(words).stream().anyMatch(word -> objetiveWords.contains(word)))
            result = 5;
        return result;
    }
}
