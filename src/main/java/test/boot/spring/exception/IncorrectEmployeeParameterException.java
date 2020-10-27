package test.boot.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class IncorrectEmployeeParameterException extends RuntimeException {
    public IncorrectEmployeeParameterException(String message) {
        super(message);
    }
}
