package peakle_time.peakle_time.Member.dto;

import peakle_time.peakle_time.global.Location;

public record JoinRequest(

        String loginId,
        String password,
        String checkPassword,
        String nickname,
        String email,
        String company


) {
}
