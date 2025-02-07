package org.example.calendar_advanced.global.error.exception;

import org.example.calendar_advanced.global.error.ErrorCode;

public class Exception409 extends RuntimeException{
    private ErrorCode errorCode;

    public Exception409(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }
}
