package org.example.calendar_advanced.global.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 400 에러

    // 401 에러
    INVALID_PASSWORD("비밀번호가 잘못되었습니다.", HttpStatus.UNAUTHORIZED),

    // 404 에러
    SCHEDULE_NOT_FOUND("scheduleId에 해당하는 일정이 없습니다.", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("userId에 해당하는 유저가 없습니다.", HttpStatus.NOT_FOUND),

    //409 에러
    EMAIL_ALREADY_EXISTS("이미 사용 중인 이메일입니다.", HttpStatus.CONFLICT);

    private String message;
    private HttpStatus httpStatus;

    private ErrorCode(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
