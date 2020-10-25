package test.boot.spring.exception;

public class IncorrectEmployeeParameterException extends RuntimeException {
    public IncorrectEmployeeParameterException(String message) {
        super(message);
    }
}
