package peakle_time.peakle_time.Member.dto;

public record UpdateRequest(
        String nickname,
        String email,
        String company
) {
}
