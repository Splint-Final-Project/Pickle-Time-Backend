package pickle_time.pickle_time.User.dto.response;

import pickle_time.pickle_time.Scrap.dto.ScrapResponse;
import pickle_time.pickle_time.User.Role;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.global.auth.oauth.ProviderType;

import java.util.List;

public record UserInfoResponse (
    Long id,
    String nickname,
    Role status,
    ProviderType socialType,
    String company,
    String imageUrl,
    List<ScrapResponse> scraps
) {}



