package pickle_time.pickle_time.global.auth.dto;

import lombok.Getter;

@Getter
public class JwtDTO {
    private Boolean success = Boolean.TRUE;
    private String token;
    private String error = null;

    public JwtDTO(String token) {
        this.token = token;
    }

}
