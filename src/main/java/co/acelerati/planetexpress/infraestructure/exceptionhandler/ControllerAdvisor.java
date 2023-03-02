package co.acelerati.planetexpress.infraestructure.exceptionhandler;

import co.acelerati.planetexpress.infraestructure.exception.NoDataFoundException;
import co.acelerati.planetexpress.infraestructure.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";
    private static final String WARNING = "warning";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String,String>> hadleNoDataFoundException(NoDataFoundException ignoredNoDataFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE,ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String,String>> hadleUnauthorizedException(UnauthorizedException ignoredUnauthorizedException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap(WARNING,ExceptionResponse.ROLE_UNAUTHORIZED.getMessage()));
    }
}
