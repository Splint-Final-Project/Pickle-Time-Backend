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
import pickle_time.pickle_time.global.dto.ErrorResponse;
import pickle_time.pickle_time.global.exception.ErrorCode;

import java.io.IOException;
import java.io.OutputStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String token = request.getHeader("Authorization");
        this.sendErrorMessage(response, token);

    }

    private void sendErrorMessage(
                                  HttpServletResponse response
                                  , String token
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

            if (token != null) objectMapper.writeValue(os, ErrorResponse.of(ErrorCode.INVALID_TOKEN));
            else objectMapper.writeValue(os, ErrorResponse.of(ErrorCode.NOT_FOUND_TOKEN));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
