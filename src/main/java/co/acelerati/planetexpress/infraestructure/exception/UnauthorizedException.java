package co.acelerati.planetexpress.infraestructure.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message, Throwable cause) { super(message, cause);}
}