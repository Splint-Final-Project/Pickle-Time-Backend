package pickle_time.pickle_time.Pickle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pickle_time.pickle_time.Pickle.dto.request.CreatePickleRequest;
import pickle_time.pickle_time.Pickle.dto.request.UpdatePickleRequest;
import pickle_time.pickle_time.Pickle.model.Pickle;
import pickle_time.pickle_time.Pickle.model.PickleStatus;
import pickle_time.pickle_time.Pickle.repository.PickleRepository;
import pickle_time.pickle_time.User.Repository.UserRepository;
import pickle_time.pickle_time.User.model.Users;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Transactional
@RequiredArgsConstructor
public class PickleService {

    private final PickleRepository pickleRepository;
    private final UserRepository userRepository;

    public Pickle createPickle(Long userId, CreatePickleRequest createPickleRequest) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Pickle pickle = Pickle.builder()
                .user(user)
                .latitude(createPickleRequest.latitude())
                .longitude(createPickleRequest.longitude())
                .pickleType(createPickleRequest.pickleType())
                .title(createPickleRequest.title())
                .startDate(createPickleRequest.startDate())
                .endDate(createPickleRequest.endDate())
                .price(createPickleRequest.price())
                .auth(createPickleRequest.auth())
                .category(createPickleRequest.category())
                .capacity(createPickleRequest.capacity())
                .pickleStatus(PickleStatus.RECRUITING)
                .content(createPickleRequest.content())
                .build();

        return pickleRepository.save(pickle);
    }

    public Optional<Pickle> findById(Long id) {
        return pickleRepository.findById(id);
    }

    public Pickle updatePickle(Long id, UpdatePickleRequest updatePickleRequest) {
        Pickle pickle = pickleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("피클이 존재하지 않습니다."));

        pickle.pickleUpdate(updatePickleRequest.title(), updatePickleRequest.content(), updatePickleRequest.latitude(),
                updatePickleRequest.longitude(), updatePickleRequest.pickleType(), updatePickleRequest.startDate(),
                updatePickleRequest.endDate(), updatePickleRequest.price(), updatePickleRequest.auth(),
                updatePickleRequest.category(), updatePickleRequest.capacity());

        return pickleRepository.save(pickle);
    }

    public List<Pickle> findAll() {
        return pickleRepository.findAll();
    }

    public Pickle updatePickleStatus(Long id, PickleStatus status) {
        Pickle pickle = pickleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("피클이 존재하지 않습니다."));
        pickle.changeStatus(status);
        return pickleRepository.save(pickle);
    }

    public List<Pickle> findPicklesByUserParticipation(Long userId) {
//        userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return pickleRepository.findByParticipantsUserId(userId);
    }
    /*
    public List<Pickle> findPicklesByUserParticipation(Long userId) {
        List<Participant> participants = participantRepository.findByUsersId(userId);
        return participants.stream()
                .map(Participant::getPickle)
                .collect(Collectors.toList());
    }

     */

    public Pickle endPickle(Long id) {
        return updatePickleStatus(id, PickleStatus.End);
    }

    public void deletePickle(Long id) {
        Pickle pickle = pickleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("피클이 존재하지 않습니다."));
        pickleRepository.delete(pickle);
    }
}
