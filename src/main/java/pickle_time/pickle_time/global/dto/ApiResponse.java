package pickle_time.pickle_time.global.dto;

public record ApiResponse<T>(
        boolean success,
        T response,
        ErrorResponse error
) {
}