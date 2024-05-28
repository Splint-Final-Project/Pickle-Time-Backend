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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PickleService {

  private final PickleRepository pickleRepository;
  private final UserRepository userRepository;

  public Pickle createPickle(CreatePickleRequest createPickleRequest) {
    Users user = userRepository.findById(createPickleRequest.userId())
            .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

    Pickle pickle = Pickle.builder()
            .users(user)
            .title(createPickleRequest.title())
            .content(createPickleRequest.content())
            .latitude(createPickleRequest.latitude())
            .longitude(createPickleRequest.longitude())
            .pickleStatus(PickleStatus.RECRUITING)
            .capacity(createPickleRequest.capacity())
            .build();

    return pickleRepository.save(pickle);
  }
  public Pickle findById(Long id) {
    return pickleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 피클이 존재하지 않습니다."));
  }

  public List<Pickle> findAll() {
    return pickleRepository.findAll();
  }

  public Pickle updatePickle(Long id, UpdatePickleRequest updatePickleRequest) {
    Pickle pickle = pickleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 피클이 존재하지 않습니다."));

    pickle.update(
            updatePickleRequest.title(),
            updatePickleRequest.content(),
            updatePickleRequest.latitude(),
            updatePickleRequest.longitude(),
            updatePickleRequest.capacity()
    );

    return pickleRepository.save(pickle);
  }

  public void deletePickle(Long id) {
    Pickle pickle = pickleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 피클이 존재하지 않습니다."));
    pickleRepository.delete(pickle);
  }

}
