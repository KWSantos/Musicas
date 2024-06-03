package example.com.musics.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import example.com.musics.common.ConversorDate;
import example.com.musics.domain.exception.ResourceBadRequestException;
import example.com.musics.domain.exception.ResourceNotFoundException;
import example.com.musics.domain.model.ErrorResponse;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
        String timeDate = ConversorDate.converseDateToTimeDate(new Date());
        ErrorResponse error = new ErrorResponse(timeDate, HttpStatus.NOT_FOUND.value(), "NOT FOUND", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorResponse> handlerBadRequestFoundException(ResourceBadRequestException ex){
        String timeDate = ConversorDate.converseDateToTimeDate(new Date());
        ErrorResponse error = new ErrorResponse(timeDate, HttpStatus.BAD_REQUEST.value(), "NOT FOUND", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerRequestException(Exception ex) {
        String timeDate = ConversorDate.converseDateToTimeDate(new Date());
        ErrorResponse error = new ErrorResponse(timeDate, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
