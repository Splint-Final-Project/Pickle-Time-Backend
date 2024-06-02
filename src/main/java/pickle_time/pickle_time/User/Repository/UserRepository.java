package pickle_time.pickle_time.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.global.auth.oauth.ProviderType;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmailAndProviderType(String email, ProviderType providerType);
    boolean existsByNickname(String nickname);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailAndProviderType(String email, ProviderType provider);

}
