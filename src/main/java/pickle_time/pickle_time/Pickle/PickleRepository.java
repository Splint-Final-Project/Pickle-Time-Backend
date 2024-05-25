package pickle_time.pickle_time.Pickle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickleRepository extends JpaRepository<Pickle,Long> {
}
