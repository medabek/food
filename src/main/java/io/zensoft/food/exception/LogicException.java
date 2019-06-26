package io.zensoft.food.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class LogicException extends RuntimeException {
    public LogicException(String s) {
        super(s);
    }
}
