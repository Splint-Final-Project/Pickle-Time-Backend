package pickle_time.pickle_time.global.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import pickle_time.pickle_time.global.auth.exception.UserNotExistException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class OAuth2FailerHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.setCharacterEncoding("utf-8");

        UserNotExistException userNotExistException = (UserNotExistException) exception;
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("response", userNotExistException.getOAuth2UserInfo());

        String jsonResponse = objectMapper.writeValueAsString(map);
        response.getWriter().write(jsonResponse);

    }
}
