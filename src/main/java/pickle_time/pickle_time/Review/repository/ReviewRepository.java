package pickle_time.pickle_time.Review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pickle_time.pickle_time.Review.model.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPickleId(Long pickleId);
}
