package pickle_time.pickle_time.global.auth.oauth;

public enum ProviderType {
    KAKAO("kakao"),
    NAVER("naver"),
    GENERAL("GENERAL");
    private final String provider;

    ProviderType(String provider) {
        this.provider = provider;
    }
}
