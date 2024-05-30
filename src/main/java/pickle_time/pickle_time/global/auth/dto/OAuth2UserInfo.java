package pickle_time.pickle_time.global.auth.dto;


import lombok.Builder;

import java.util.Map;

@Builder
public record OAuth2UserInfo(
        String name,
        String email,
        String profile
) {
    public static  OAuth2UserInfo of(String registrationId, Map<String, Object> attributes)  {
        return switch (registrationId) {
            case "kakao" -> ofKaKao(attributes);
            default -> throw new IllegalStateException("Unexpected value: " + registrationId);
        };
    }

    public static OAuth2UserInfo ofKaKao(Map<String, Object> attributes) {

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");



        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");


        return OAuth2UserInfo.builder()
                .name((String) profile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .profile((String) profile.get("profile_image_url"))
                .build();
    }
}
