package pickle_time.pickle_time.Member.dto;

public record JoinRequest(

        String email,
        String password,
        String checkPassword,
        String nickname,
        String company,
        String imageUrl

) {
}
