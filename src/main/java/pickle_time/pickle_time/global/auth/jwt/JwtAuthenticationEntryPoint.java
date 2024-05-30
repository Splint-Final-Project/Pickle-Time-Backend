package pickle_time.pickle_time.global.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import pickle_time.pickle_time.global.ErrorMessage;

import java.io.IOException;
import java.io.OutputStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error(authException.getMessage());
        this.sendErrorMessage(new BadCredentialsException("로그인이 필요합니다.(인증 실패)"), response);
    }

    private void sendErrorMessage(Exception authenticationException,
                                  HttpServletResponse response
    ) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        try {
            OutputStream os = response.getOutputStream(); // 응답 body write
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Seoul")));

            javaTimeModule.addSerializer(localDateTimeSerializer); // 직렬화 방식 add
            ObjectMapper objectMapper = new ObjectMapper().registerModule(javaTimeModule); // LocalDateTime serialize
            objectMapper.writeValue(os, new ErrorMessage(HttpStatus.UNAUTHORIZED, authenticationException.getMessage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
