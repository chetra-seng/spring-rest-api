package meow.spring.restapi.exceptions;

import meow.spring.restapi.responses.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<ExceptionResponse>
                (
                        new ExceptionResponse(ex.getMessage(),
                        new Date(),
                        request.getDescription(false)),
                        HttpStatus.NOT_FOUND
                );
    }
}
