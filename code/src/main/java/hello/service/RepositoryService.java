package hello.service;

import hello.repository.PictureRepository;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class RepositoryService {

    @Autowired
    private PictureRepository pictureRepository;

    public static final Map<Class<?>, Repository<?, ?>> repos = new HashMap<>();

    @PostConstruct
    private void postConstruct() {
        repos.put(PictureRepository.class, pictureRepository);
    }


}
