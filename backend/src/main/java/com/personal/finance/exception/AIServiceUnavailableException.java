package com.personal.finance.exception;

public class AIServiceUnavailableException extends RuntimeException {
    public AIServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
