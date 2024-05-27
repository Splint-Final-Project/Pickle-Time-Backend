package pickle_time.pickle_time.User.dto;

public record UserJoinRequest(

        String email,
        String password,
        String checkPassword,
        String nickname,
        String company,
        String imageUrl

) {
}
