package pickle_time.pickle_time.User.dto.response;

public record UserProfileResponse(
        String nickname,
        String email,
        String company,
        String imageUrl

) {
}
