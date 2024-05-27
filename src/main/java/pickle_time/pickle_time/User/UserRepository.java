package pickle_time.pickle_time.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<Users> findByEmail(String email);

}