package pickle_time.pickle_time.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pickle_time.pickle_time.global.dto.ApiResponse;
import pickle_time.pickle_time.global.dto.ErrorResponse;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ApiResponse<>(false, null, new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE.getStatus(), e.getMessage()));
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<ErrorResponse> handleNotFoundException(ChangeSetPersister.NotFoundException e) {
        return new ApiResponse<>(false, null, new ErrorResponse(ErrorCode.USER_NOT_FOUND.getStatus(), e.getMessage()));
    }

    // 추가적인 예외 핸들러
}
