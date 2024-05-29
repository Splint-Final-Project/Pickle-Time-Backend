package pickle_time.pickle_time.Pickle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pickle_time.pickle_time.Pickle.model.Pickle;

@Repository
public interface PickleRepository extends JpaRepository<Pickle,Long> {
}
