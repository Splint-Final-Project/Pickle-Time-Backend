package pickle_time.pickle_time.User.dto;

public record UserUpdateRequest(
        String nickname,
        String email,
        String company,
        String imageUrl
) {
}
