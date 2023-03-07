package co.acelerati.planetexpress.infraestructure.exceptionhandler;

import co.acelerati.planetexpress.infraestructure.exception.NoDataFoundException;
import co.acelerati.planetexpress.infraestructure.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";
    private static final String WARNING = "warning";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
      NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE,
          ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, String>> handleUnauthorizedException(
      UnauthorizedException ignoredUnauthorizedException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap(WARNING,
          ExceptionResponse.ROLE_UNAUTHORIZED.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        String errorMessage = "";
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("{ error: ");
            violations.forEach(violation -> builder.append(violation.getMessage() + " , "));
            builder.append(" }");
            errorMessage = builder.toString();
            errorMessage.replace(" ,  }", " }");
        } else {
            errorMessage = "ConstraintViolationException occured.";
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
