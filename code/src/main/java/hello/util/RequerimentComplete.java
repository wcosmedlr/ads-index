package hello.util;

import hello.entity.Ad;

public class RequerimentComplete extends Requeriment {

    @Override
    public int process(Ad ad) {
        int result = 0;
        boolean base = !ad.getDescription().isEmpty() && ad.getPictures().size() > 0;
        switch(ad.getTypology()){
            case "CHALET":
                base = base && ad.getGardenSize() > 0;
            case "FLAT:":
                base = base && ad.getHouseSize() > 0;
        }
        if(base)
            result = 40;
        return result;
    }
}
