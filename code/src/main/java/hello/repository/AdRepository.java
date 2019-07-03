package hello.repository;

import hello.entity.Ad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdRepository extends CrudRepository<Ad, Long> {
    List<Ad> findAdsByDescriptionAndTypology(String description, String typology);
    List<Ad> findAdsByEvaluationIsGreaterThanEqualOrderByEvaluationDesc(int evaluation);
}
