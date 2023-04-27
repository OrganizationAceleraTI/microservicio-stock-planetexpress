package co.acelerati.planetexpress.domain.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
