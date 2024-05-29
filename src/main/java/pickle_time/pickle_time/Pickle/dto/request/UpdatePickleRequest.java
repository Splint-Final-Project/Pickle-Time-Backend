package pickle_time.pickle_time.Pickle.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pickle_time.pickle_time.Pickle.model.PickleType;

import java.time.LocalDate;

public record UpdatePickleRequest(
        @NotNull(message = "올바르지 않은 위도입니다.") Double latitude,
        @NotNull(message = "올바르지 않은 경도입니다.") Double longitude,
        PickleType pickleType,
        @NotBlank(message = "스터디 제목을 입력해 주세요") String title,
        @NotNull(message = "시작 시간을 입력해 주세요") LocalDate startDate,
        @NotNull(message = "끝나는 시간을 입력해 주세요") LocalDate endDate,
        Integer price,
        Integer auth,
        String category,
        @Min(value = 2, message = "정원은 {value}명 이상이어야 합니다.") Integer capacity,
        @NotBlank(message = "내용을 입력해 주세요") String content
) {
}
