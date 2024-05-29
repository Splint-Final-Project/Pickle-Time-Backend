package pickle_time.pickle_time.global.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import pickle_time.pickle_time.User.Users;
import pickle_time.pickle_time.global.auth.dto.PrincipalDetails;
import pickle_time.pickle_time.global.jwt.TokenProvider;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private static final String redirectURI = "http://localhost:3000/auth";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String accessToken = tokenProvider.generateToken(authentication);

        PrincipalDetails principal  = (PrincipalDetails) authentication.getPrincipal();
        Users user = principal.getUser();

        log.info(user.getStatus());

        if (user.getStatus().equals("PENDING")) {
            String redirectUrl = UriComponentsBuilder.fromUriString(redirectURI + "/signup")
                    .queryParam("accessToken", accessToken)
                    .build().toUriString();
            response.sendRedirect(redirectUrl);
        }
        else {
            String redirectUrl = UriComponentsBuilder.fromUriString(redirectURI + "/success")
                    .queryParam("accessToken", accessToken)
                    .build().toUriString();
            response.sendRedirect(redirectUrl);
        }
    }
}
