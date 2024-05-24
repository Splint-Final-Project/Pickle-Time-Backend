package peakle_time.peakle_time.Member.dto;

public record JoinRequest(

        String loginId,
        String password,
        String nickname,
        String email,
        Long age

) {
}
