package pickle_time.pickle_time.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pickle_time.pickle_time.User.dto.UserJoinRequest;
import pickle_time.pickle_time.User.dto.UserLoginRequest;
import pickle_time.pickle_time.User.dto.UserUpdateRequest;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor // final 붙으면 autowire 됨
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * email 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * nickname 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public Users join(UserJoinRequest request) {
        if (checkEmailDuplicate(request.email())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        if (checkNicknameDuplicate(request.nickname())) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
        if (!request.password().equals(request.checkPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(request.password());

        Users users = Users.builder()
                .password(encodedPassword)
                .nickname(request.nickname())
                .email(request.email())
                .company(request.company())
                .imageUrl(request.imageUrl())
                .build();

        return userRepository.save(users);
    }

    public Users login(UserLoginRequest userLoginRequest) {
        Users users = userRepository.findByEmail(userLoginRequest.email())
                .orElseThrow(() -> new IllegalArgumentException("해당 로그인 아이디가 존재하지 않습니다."));

        if (!bCryptPasswordEncoder.matches(userLoginRequest.password(), users.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return users;
    }

    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Users updateMember(Long id, UserUpdateRequest userUpdateRequest) {
        Users users = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        users.update(userUpdateRequest.nickname(), userUpdateRequest.email(), userUpdateRequest.company(), userUpdateRequest.imageUrl());

        return userRepository.save(users);
    }

    public void deleteMember(Long id) {
        Users users = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        userRepository.delete(users);
    }
}
