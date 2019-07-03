package hello.util;

import hello.entity.Ad;

public class RequerimentSize extends Requeriment {

    @Override
    public int process(Ad ad) {
        int result = 0;
        String[] words =  ad.getDescription().split("\\s+");
        if(ad.getTypology().equals("FLAT")) {
            if(words.length > 50)
                result = 30;
            else if(words.length > 20)
                result = 10;
        }
        else if(ad.getTypology().equals("CHALET") && words.length > 50)
            result = 20;
        return result;
    }
}
