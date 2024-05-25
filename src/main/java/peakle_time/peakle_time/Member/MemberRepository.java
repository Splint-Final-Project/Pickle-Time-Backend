package peakle_time.peakle_time.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member getById(final Long id) {
        return findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다."));
    }

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Optional<Member> findByEmail(String email);

}
