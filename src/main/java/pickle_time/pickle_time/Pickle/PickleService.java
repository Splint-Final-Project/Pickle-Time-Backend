package pickle_time.pickle_time.Pickle;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PickleService {

  private final PickleRepository pickleRepository;


}
