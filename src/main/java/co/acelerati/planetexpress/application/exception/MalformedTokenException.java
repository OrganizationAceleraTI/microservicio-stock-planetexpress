package co.acelerati.planetexpress.application.exception;

public class MalformedTokenException extends RuntimeException {

    public MalformedTokenException() {
    }

    public MalformedTokenException(String message) {
        super(message);
    }
}
