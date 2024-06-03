package pickle_time.pickle_time.Pickle.dto.response;

import lombok.Builder;
import pickle_time.pickle_time.Review.dto.response.ReviewInfoResponse;
import pickle_time.pickle_time.User.dto.response.UserInfoResponse;

import java.util.List;

@Builder
public record PickleInfoResponse(
        Long id,
        String title,
        String content,
        double latitude,
        double longitude,
        int capacity,
        String pickleStatus
) {
}
