package pickle_time.pickle_time.User.dto.response;

import lombok.Builder;
import pickle_time.pickle_time.Scrap.dto.ScrapResponse;
import pickle_time.pickle_time.User.Role;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.global.auth.oauth.ProviderType;

import java.util.List;

@Builder
public record UserInfoResponse (
    Long id,
    String nickname,
    String email,
    Role status,
    ProviderType socialType,
    String company,
    String imageUrl,
    List<ScrapResponse> scraps
) {}



