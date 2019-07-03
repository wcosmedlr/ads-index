package hello.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hello.entity.Picture;
import hello.repository.PictureRepository;
import hello.service.PictureService;
import hello.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class PictureDeserializer extends StdDeserializer<List<Picture>> {
    private static final long serialVersionUID = 1L;

    public PictureDeserializer() {
        this(null);
    }

    protected PictureDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<Picture> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ArrayList<Picture> pictures = new ArrayList<Picture>();
        List<Integer> picturesIds = p.readValueAs(List.class);
        PictureRepository  pictureRepository = (PictureRepository) RepositoryService.repos.get(PictureRepository.class);
        picturesIds.forEach(pictureId -> {
            pictures.add(pictureRepository.getOne(pictureId.longValue()));
        });
        return pictures;
    }


}
