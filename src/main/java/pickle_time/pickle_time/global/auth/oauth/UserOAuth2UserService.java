package pickle_time.pickle_time.global.auth.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import pickle_time.pickle_time.User.Repository.UserRepository;
import pickle_time.pickle_time.User.model.Users;

import pickle_time.pickle_time.global.auth.PrincipalDetails;

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
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, attributes);

        Optional<Users> member= memberRepository.findByEmailAndProviderType(oAuth2UserInfo.email(), oAuth2UserInfo.providerType());
        if (member.isEmpty()) {
            Users users = memberRepository.save(new Users(oAuth2UserInfo.email(), oAuth2UserInfo.name(), oAuth2UserInfo.providerType(), oAuth2UserInfo.profile()));
            return new PrincipalDetails(users, attributes, userNameAttributeName);
        }

        return new PrincipalDetails(member.get(), attributes, userNameAttributeName );
    }
}
