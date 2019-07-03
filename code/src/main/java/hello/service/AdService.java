package hello.service;

import hello.entity.Ad;
import hello.repository.AdRepository;
import hello.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdService implements IAdService{
    @Autowired
    private AdRepository adRepository;

    List<Requeriment> requeriments;

    public AdService(){
        requeriments = new ArrayList<Requeriment>();
        requeriments.add(new RequerimentDescription());
        requeriments.add(new RequerimentDescriptionLike());
        requeriments.add(new RequerimentPicture());
        requeriments.add(new RequerimentPictureType());
        requeriments.add(new RequerimentSize());
        requeriments.add(new RequerimentComplete());
    }

    @Override
    public Ad getAdById(long AdId) {
        Ad obj = adRepository.findById(AdId).get();
        return obj;
    }
    @Override
    public List<Ad> getAllAds(){
        List<Ad> list = new ArrayList<Ad>();
        adRepository.findAll().forEach(e -> list.add(e));
        return list;
    }
    @Override
    public synchronized boolean addAd(Ad ad){
        List<Ad> list = adRepository.findAdsByDescriptionAndTypology(ad.getDescription(), ad.getTypology());
        if (list.size() > 0) {
            return false;
        } else {
            adRepository.save(ad);
            return true;
        }
    }
    @Override
    public void updateAd(Ad ad) {
        adRepository.save(ad);
    }
    @Override
    public void deleteAd(int adId) {
        adRepository.delete(getAdById(adId));
    }

    public List<Ad> getNotIrrelevantAds(){
        List<Ad> list = new ArrayList<Ad>();
        adRepository.findAdsByEvaluationIsGreaterThanEqualOrderByEvaluationDesc(40).forEach(e -> list.add(e));
        return list;
    }
    public void processAds(List<Ad> ads){
        ads.forEach(ad -> {
            int evaluation = requeriments.stream().mapToInt(requeriment -> requeriment.analyze(ad)).sum();
            if(evaluation < 0)
                evaluation = 0;
            else if (evaluation > 100)
                evaluation = 100;
            if(ad.getEvaluation() == null || (ad.getEvaluation() >= 40 && evaluation < 40 || ad.getEvaluation() < 40 && evaluation >= 40)){
                ad.setDateIrrelevantChange(new Date());
            }
            ad.setEvaluation(evaluation);
            this.updateAd(ad);
        });
    }
}
