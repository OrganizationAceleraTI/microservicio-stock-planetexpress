package co.acelerati.planetexpress.domain.exception;

public class MalformedTokenException extends RuntimeException {

    public MalformedTokenException() {
    }

    public MalformedTokenException(String message) {
        super(message);
    }
}
