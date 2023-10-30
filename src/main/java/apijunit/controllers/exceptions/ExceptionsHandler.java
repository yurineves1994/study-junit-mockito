package apijunit.controllers.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import apijunit.services.exceptions.DataIntegratyViolationException;
import apijunit.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

    StandardError err = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(),
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  @ExceptionHandler(DataIntegratyViolationException.class)
  public ResponseEntity<StandardError> DataIntegratyViolationException(DataIntegratyViolationException e, HttpServletRequest request) {

    StandardError err = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(),
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }
}
