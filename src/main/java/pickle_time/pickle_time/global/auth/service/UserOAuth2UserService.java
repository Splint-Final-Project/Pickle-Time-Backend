package pickle_time.pickle_time.global.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import pickle_time.pickle_time.User.UserRepository;
import pickle_time.pickle_time.User.Users;
import pickle_time.pickle_time.global.auth.dto.OAuth2UserInfo;
import pickle_time.pickle_time.global.auth.dto.PrincipalDetails;
import pickle_time.pickle_time.global.auth.exception.UserNotExistException;

import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User  = super.loadUser(userRequest);

        // kakao or naver
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // Auth Server 로부터 받아오는 유저 정보.

        // kakao_account
        // nickname
        // profile_image
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // userNameAttributeName
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        // User 정보 DTO
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, attributes);


        // TODO : 카카오 이메일 <-> 네이버 이메일
        // TODO : Redirect URL 형식을 바꾸기. => 토큰 전달 시, 특정 페이지로 리다이렉트 시키기!
        // TODO : 일반 이메일 로그인 시, 토큰 발급.
        // TODO : 배포드리기.

        // TODO :

        // TODO :  정보 전달은 쿼리스트링 (redirect 조사)


        // * TODO : 토큰 검증할 때, Users 테이블에 존재하는지 검증하기. (생각해보기)
        Optional<Users> member= memberRepository.findByEmail(oAuth2UserInfo.email());
        if (member.isEmpty()) {
            Users users = memberRepository.save(new Users(oAuth2UserInfo.email(), oAuth2UserInfo.name(), registrationId,oAuth2UserInfo.profile()));

            return new PrincipalDetails(users, attributes, userNameAttributeName);
        }

        // OAuth2User 로 반환.
        return new PrincipalDetails(member.get(), attributes, userNameAttributeName );
    }
}
