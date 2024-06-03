package pickle_time.pickle_time.User.dto.request;

public record UserUpdateRequest(
        String nickname,
        String company,
        String imageUrl
) {
}
