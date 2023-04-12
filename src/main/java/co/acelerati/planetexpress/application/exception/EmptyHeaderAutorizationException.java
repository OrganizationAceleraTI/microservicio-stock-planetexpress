package co.acelerati.planetexpress.application.exception;

public class EmptyHeaderAutorizationException extends RuntimeException {

    public EmptyHeaderAutorizationException() {
    }

    public EmptyHeaderAutorizationException(String message) {
        super(message);
    }
}
