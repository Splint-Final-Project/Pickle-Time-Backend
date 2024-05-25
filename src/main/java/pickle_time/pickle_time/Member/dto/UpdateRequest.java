package pickle_time.pickle_time.Member.dto;

public record UpdateRequest(
        String nickname,
        String email,
        String company
) {
}
