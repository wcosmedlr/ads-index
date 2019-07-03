package hello.util;

import hello.entity.Ad;

public class RequerimentPicture extends Requeriment {

    @Override
    public int process(Ad ad) {
        int result = 0;
        if(ad.getPictures().size() == 0)
            result = -10;
        return result;
    }
}
