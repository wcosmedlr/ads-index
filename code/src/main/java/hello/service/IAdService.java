package hello.service;

import hello.entity.Ad;

import java.util.List;

public interface IAdService {
        List<Ad> getAllAds();
        Ad getAdById(long AdId);
        boolean addAd(Ad Ad);
        void updateAd(Ad Ad);
        void deleteAd(int AdId);
}
