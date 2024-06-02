package pickle_time.pickle_time.Participant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pickle_time.pickle_time.Participant.model.Participant;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByUsersId(Long userId);
}
