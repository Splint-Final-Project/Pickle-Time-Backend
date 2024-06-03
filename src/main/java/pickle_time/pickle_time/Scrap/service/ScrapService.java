package pickle_time.pickle_time.Scrap.service;


import org.springframework.stereotype.Service;
import pickle_time.pickle_time.Pickle.model.Pickle;
import pickle_time.pickle_time.Pickle.repository.PickleRepository;
import pickle_time.pickle_time.Scrap.model.Scrap;
import pickle_time.pickle_time.Scrap.repository.ScrapRepository;
import pickle_time.pickle_time.User.Repository.UserRepository;
import pickle_time.pickle_time.User.model.Users;

import java.util.List;

@Service
public class ScrapService {
    private ScrapRepository scrapRepository;
    private UserRepository userRepository;
    private PickleRepository pickleRepository;

    public ScrapService(ScrapRepository scrapRepository, UserRepository userRepository, PickleRepository pickleRepository) {
        this.scrapRepository = scrapRepository;
        this.userRepository = userRepository;
        this.pickleRepository = pickleRepository;
    }

    public Scrap scrap(Long userId, Long pickleId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        boolean alreadyScrapped = user.getScraps().stream()
                .anyMatch(scrap -> scrap.getPickle().getId().equals(pickleId));
        if (alreadyScrapped) {
            throw new IllegalArgumentException("이미 찜한 피클입니다.");
        }


        Pickle pickle = pickleRepository.findById(pickleId).orElseThrow(() -> new IllegalArgumentException("해당 피클이 존재하지 않습니다."));
        Scrap scrap = Scrap.builder().pickle(pickle).user(user).pickle(pickle).build();
        return scrapRepository.save(scrap);
    }

    public Scrap cancel(Long userId, Long pickleId) {
        Scrap scrap = scrapRepository.findByUserIdAndPickleId(userId, pickleId).orElseThrow(() -> new IllegalArgumentException("해당 피클에 대한 찜이 존재하지 않습니다."));
        scrapRepository.delete(scrap);
        return scrap;
    }
}
