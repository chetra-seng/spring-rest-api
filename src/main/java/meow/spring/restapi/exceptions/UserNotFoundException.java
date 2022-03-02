package meow.spring.restapi.exceptions;

import java.util.Date;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
