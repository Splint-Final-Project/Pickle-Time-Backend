package pickle_time.pickle_time.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pickle_time.pickle_time.Scrap.model.Scrap;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.global.auth.oauth.ProviderType;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmailAndProviderType(String email, ProviderType providerType);
    boolean existsByNickname(String nickname);
    Optional<Users> findById(Long id);
    Optional<Users> findByEmailAndProviderType(String email, ProviderType provider);


    List<Scrap> findAllById(Long id);
}
