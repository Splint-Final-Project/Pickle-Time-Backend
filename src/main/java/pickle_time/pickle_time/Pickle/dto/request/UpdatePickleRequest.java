package pickle_time.pickle_time.Pickle.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePickleRequest(
        @NotNull(message = "올바르지 않은 경도입니다.") Double longitude,
        @NotNull(message = "올바르지 않은 위도입니다.") Double latitude,
        @NotBlank(message = "스터디 제목을 입력해 주세요") String title,
        @NotBlank(message = "내용을 입력해 주세요") String content,
        @Min(value = 2, message = "정원은 {value}명 이상이어야 합니다.") @Max(value = 8, message = "정원은 {value}명 이하여야 합니다. ") Integer capacity

) {
}
