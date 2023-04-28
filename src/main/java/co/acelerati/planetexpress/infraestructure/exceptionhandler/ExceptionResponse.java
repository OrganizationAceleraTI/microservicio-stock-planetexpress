package co.acelerati.planetexpress.infraestructure.exceptionhandler;

public enum ExceptionResponse {

    NO_DATA_FOUND("No data found for the request petition"),
    ROLE_UNAUTHORIZED("The user is not authorized to make the request"),

    BAD_REQUEST("There is invalid values in the data provided for the request");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
