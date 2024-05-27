package pickle_time.pickle_time.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserJoinRequest(

        @Email(message = "이메일 형식대로 입력해 주세요") String email,
        @NotBlank(message = "비밀번호를 입력해 주세요") String password,
        @NotBlank(message = "비밀번호를 입력해 주세요") String checkPassword,
        @NotBlank(message = "닉네임을 입력해 주세요") String nickname,
        String company,
        String imageUrl

) {
}
