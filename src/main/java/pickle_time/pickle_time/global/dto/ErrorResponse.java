package pickle_time.pickle_time.global.dto;


import pickle_time.pickle_time.global.exception.ErrorCode;

public record ErrorResponse(
        int code,
        String message
) {

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code.getStatus(), code.getMessage());
    }

}
