package pickle_time.pickle_time.Scrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pickle_time.pickle_time.Scrap.model.Scrap;

import java.util.List;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap,Integer> {
    Optional<Scrap> findByUserIdAndPickleId(Long userId, Long pickleId);
    List<Scrap> findByUserId(Long userId);


}
