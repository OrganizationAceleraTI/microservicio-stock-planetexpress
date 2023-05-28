package co.acelerati.planetexpress.infraestructure.exceptionhandler;

import co.acelerati.planetexpress.domain.exception.BadRequestException;
import co.acelerati.planetexpress.domain.exception.NotFoundException;
import co.acelerati.planetexpress.domain.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {

    private static final String MESSAGE = "message";
    private static final String WARNING = "warning";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
      NotFoundException ignoredNoDataFoundException) {
        log.info(ignoredNoDataFoundException.getMessage(), ignoredNoDataFoundException.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE,
          ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, String>> handleUnauthorizedException(
      UnauthorizedException ignoredUnauthorizedException) {
        log.warn(ignoredUnauthorizedException.getMessage(), ignoredUnauthorizedException.getCause());
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
            violations.forEach(violation -> {
                builder.append(violation.getMessage() + " , ");
                log.warn(violation.getMessage());
            });
            builder.append(" }");
            errorMessage = builder.toString();
            errorMessage.replace(" ,  }", " }");
        } else {
            errorMessage = "ConstraintViolationException occured.";
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        return new ResponseEntity<>(noSuchElementException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleArgumentNotValidException(MethodArgumentNotValidException argumentNotValidException) {
        return new ResponseEntity<>(ExceptionResponse.BAD_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handreBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
