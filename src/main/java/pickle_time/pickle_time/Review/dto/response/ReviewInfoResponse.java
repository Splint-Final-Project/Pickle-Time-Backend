package pickle_time.pickle_time.Review.dto.response;

import lombok.Builder;
import pickle_time.pickle_time.Pickle.dto.response.PickleInfoResponse;
import pickle_time.pickle_time.User.dto.response.UserInfoResponse;

@Builder
public record ReviewInfoResponse(
        Long id,
        int star,
        String record,
        UserInfoResponse user,
        PickleInfoResponse pickle
) {


}
