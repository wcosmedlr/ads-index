package hello.service;

import hello.entity.Picture;

import java.util.List;

public interface IPictureService {
        List<Picture> getAllPictures();
        Picture getPictureById(long pictureId);
        boolean addPicture(Picture picture);
        void updatePicture(Picture picture);
        void deletePicture(int pictureId);
}
