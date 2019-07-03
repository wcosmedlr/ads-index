package hello.service;

import hello.entity.Picture;
import hello.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PictureService implements IPictureService{
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public Picture getPictureById(long PictureId) {
        Picture obj = pictureRepository.findById(PictureId).get();
        return obj;
    }
    @Override
    public List<Picture> getAllPictures(){
        List<Picture> list = new ArrayList<Picture>();
        pictureRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public synchronized boolean addPicture(Picture picture){
        List<Picture> list = pictureRepository.findPicturesByUrl(picture.getUrl());
        if (list.size() > 0) {
            return false;
        } else {
            pictureRepository.save(picture);
            return true;
        }
    }
    @Override
    public void updatePicture(Picture picture) {
        pictureRepository.save(picture);
    }
    @Override
    public void deletePicture(int pictureId) {
        pictureRepository.delete(getPictureById(pictureId));
    }
}
