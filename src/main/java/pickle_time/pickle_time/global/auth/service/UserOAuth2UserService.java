package pickle_time.pickle_time.global.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pickle_time.pickle_time.Member.Member;
import pickle_time.pickle_time.Member.MemberRepository;
import pickle_time.pickle_time.global.auth.dto.OAuth2UserInfo;
import pickle_time.pickle_time.global.auth.dto.PrincipalDetails;
import pickle_time.pickle_time.global.auth.exception.UserNotExistException;

import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User  = super.loadUser(userRequest);

        // kakao or naver
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // Auth Server 로부터 받아오는 유저 정보.
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // userNameAttributeName
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        // User 정보 DTO
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, attributes);

        Optional<Member> member= memberRepository.findByEmail(oAuth2UserInfo.email());
        if (member.isEmpty()) {
            log.info("In OAuth User Not Found");
            throw new UserNotExistException(oAuth2UserInfo);
        }

        // OAuth2User 로 반환.
        return new PrincipalDetails(member.get(), attributes, userNameAttributeName );
    }
}
