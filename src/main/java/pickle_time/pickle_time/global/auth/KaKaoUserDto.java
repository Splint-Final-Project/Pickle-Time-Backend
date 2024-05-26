package pickle_time.pickle_time.global.auth;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KaKaoUserDto {

    public Long id;

    @JsonProperty("kakao_account")
    public KaKaoAccount kaKaoAccount;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class KaKaoAccount {

        @JsonProperty("email")
        public String email;

        @JsonProperty("profile")
        public Profile profile;

        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Profile {
            @JsonProperty("nickname")
            public String nickname;

            @JsonProperty("profile_image_url")
            public String profileImageUrl;


        }

    }


}
