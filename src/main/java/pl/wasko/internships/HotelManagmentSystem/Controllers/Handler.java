package pl.wasko.internships.HotelManagmentSystem.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.Error;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;

import java.util.List;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException e)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
