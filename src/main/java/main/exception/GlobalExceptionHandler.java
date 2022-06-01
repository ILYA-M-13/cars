package main.exception;

import main.api.response.BadDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<BadDataResponse> handleNumberException(WrongNumberException e){
        BadDataResponse badDataResponse = new BadDataResponse();
        badDataResponse.setError("number");
        badDataResponse.setDescription(e.getMessage());
        return new ResponseEntity<>(badDataResponse, HttpStatus.BAD_REQUEST);
    }
}
