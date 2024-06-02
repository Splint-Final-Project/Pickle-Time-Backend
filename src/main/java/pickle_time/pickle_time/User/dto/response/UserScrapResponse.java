package pickle_time.pickle_time.User.dto.response;

import pickle_time.pickle_time.Scrap.dto.ScrapResponse;
import pickle_time.pickle_time.Scrap.model.Scrap;
import java.util.List;


public record UserScrapResponse(
        Long userId,
        List<ScrapResponse> scraps
) {}


