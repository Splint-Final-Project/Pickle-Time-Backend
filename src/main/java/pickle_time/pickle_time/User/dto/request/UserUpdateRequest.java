package pickle_time.pickle_time.User.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @NotBlank(message = "닉네임을 입력해 주세요") String nickname,
        @NotBlank(message = "이메일을 입력해 주세요") String email,
        String company,
        String imageUrl
) {
}
