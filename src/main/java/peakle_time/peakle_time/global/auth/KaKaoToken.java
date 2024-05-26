package peakle_time.peakle_time.global.auth;


import lombok.Getter;

@Getter
public class KaKaoToken {
    private String token_type;
    private String access_token;

    public KaKaoToken(String token_type, String access_token) {
        this.token_type = token_type;
        this.access_token = access_token;
    }
}
