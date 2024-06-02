package pickle_time.pickle_time.Pickle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pickle_time.pickle_time.Pickle.model.Pickle;

import java.util.List;

@Repository
public interface PickleRepository extends JpaRepository<Pickle,Long> {

    List<Pickle> findByParticipantsUserId(Long userId);
}
