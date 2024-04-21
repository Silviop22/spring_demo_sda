package al.sdacademy.springdemo.commons.fault;

import al.sdacademy.springdemo.commons.fault.model.BadRequestException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BadRequestException.class)
    public void handleBadRequestException(BadRequestException e) {
    
    }
}
