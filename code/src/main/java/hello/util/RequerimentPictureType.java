package hello.util;

import hello.entity.Ad;
import hello.entity.Picture;

import java.util.List;

public class RequerimentPictureType extends Requeriment {

    @Override
    public int process(Ad ad) {
        int result = 0;
        List<Picture> pictures = ad.getPictures();
        for(int i = 0; i < pictures.size(); i++){
            if(pictures.get(i).getQuality().equals("HD"))
                result += 20;
            else
                result += 10;
        }
        return result;
    }
}
