package pickle_time.pickle_time.Review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewRequest(
        @NotNull(message = "별점을 입력해 주세요")
        @Min(value = 1, message = "별점은 최소 {value} 이상이어야 합니다.")
        @Max(value = 5, message = "별점은 최대 {value} 이하여야 합니다.")
        int star,

        @NotBlank(message = "리뷰 내용을 입력해 주세요")
        String record
) {}
