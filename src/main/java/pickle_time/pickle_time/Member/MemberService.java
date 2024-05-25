package pickle_time.pickle_time.Member;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pickle_time.pickle_time.Member.dto.JoinRequest;
import pickle_time.pickle_time.Member.dto.UpdateRequest;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor // final 붙으면 autowire 됨
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * email 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkEmailDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }

    /**
     * nickname 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkNicknameDuplicate(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public Member join(JoinRequest request) {
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

        Member member = Member.builder()
                .password(encodedPassword)
                .nickname(request.nickname())
                .email(request.email())
                .company(request.company())
                .build();
        System.out.println(member);
        System.out.println("asdfasdfasfd");

        return memberRepository.save(member);
    }

    public Member login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 로그인 아이디가 존재하지 않습니다."));

        if (!bCryptPasswordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member updateMember(Long id, UpdateRequest updateRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        member.update(updateRequest.nickname(), updateRequest.email(), updateRequest.company());

        return memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        memberRepository.delete(member);
    }
}
