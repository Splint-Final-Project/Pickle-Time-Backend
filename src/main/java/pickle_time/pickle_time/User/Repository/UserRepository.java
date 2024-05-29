package pickle_time.pickle_time.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pickle_time.pickle_time.User.model.Users;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmailAndSocialType(String email, String socialType);
    boolean existsByNickname(String nickname);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailAndSocialType(String email, String socialId);

}
