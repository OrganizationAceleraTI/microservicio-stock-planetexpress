package co.acelerati.planetexpress.domain.exception;

public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException() {
        super();
    }

    public ExpiredTokenException(String message) {
        super(message);
    }
}
