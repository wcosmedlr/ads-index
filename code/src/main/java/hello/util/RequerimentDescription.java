package hello.util;

import hello.entity.Ad;
import hello.entity.Picture;

import java.util.List;

public class RequerimentDescription extends Requeriment {

    @Override
    public int process(Ad ad) {
        int result = 0;
        if(ad.getDescription() != null && !ad.getDescription().isEmpty())
            result = 5;
        return result;
    }
}
