package team.s2f.lunchroom.util.exception;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    APP_ERROR("500", HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_NOT_FOUND("422", HttpStatus.UNPROCESSABLE_ENTITY),
    DATA_ERROR("409", HttpStatus.CONFLICT),
    VALIDATION_ERROR("422", HttpStatus.UNPROCESSABLE_ENTITY),
    WRONG_REQUEST("400", HttpStatus.BAD_REQUEST),
    DUPLICATE_VOTE("202", HttpStatus.ACCEPTED);

    private final String errorCode;
    private final HttpStatus status;

    ErrorType(String errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}