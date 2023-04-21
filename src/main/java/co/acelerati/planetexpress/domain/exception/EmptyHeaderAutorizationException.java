package co.acelerati.planetexpress.domain.exception;

public class EmptyHeaderAutorizationException extends RuntimeException {

    public EmptyHeaderAutorizationException() {
    }

    public EmptyHeaderAutorizationException(String message) {
        super(message);
    }
}
