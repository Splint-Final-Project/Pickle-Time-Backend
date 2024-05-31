package pickle_time.pickle_time.global.auth.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;
import pickle_time.pickle_time.global.auth.oauth.OAuth2UserInfo;

@Getter
public class UserNotExistException extends AuthenticationException {
    private Boolean status = Boolean.FALSE;
    private OAuth2UserInfo oAuth2UserInfo;

    public UserNotExistException(OAuth2UserInfo oAuth2UserInfo) {
        super("일치하는 유저가 존재하지 않습니다.");
        this.oAuth2UserInfo = oAuth2UserInfo;
    }
}
