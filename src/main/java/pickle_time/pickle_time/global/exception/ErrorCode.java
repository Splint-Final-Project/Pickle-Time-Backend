package pickle_time.pickle_time.global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "잘못된 입력 값입니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST.value(), "잘못된 타입입니다."),
    MISSING_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "인자가 부족합니다."),
    NOT_EXIST_API(HttpStatus.BAD_REQUEST.value(), "요청 주소가 올바르지 않습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "사용할 수 없는 메서드입니다."),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN.value(),  "접근 권한이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),  "서버 에러입니다."),

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(),  "존재하지 않는 사용자입니다."),
    USER_INVALID(HttpStatus.BAD_REQUEST.value(),  "권한이 없는 사용자입니다."),

    // Auth
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(),  "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED.value(),  "만료된 토큰입니다."),
    NOT_FOUND_TOKEN(HttpStatus.UNAUTHORIZED.value(),  "로그인이 필요합니다."),
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED.value(),  "유효하지 않은 리프레쉬 토큰입니다."),
    NOT_FOUND_COOKIE(HttpStatus.UNAUTHORIZED.value(),  "쿠키를 찾을 수 없습니다.");


    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
