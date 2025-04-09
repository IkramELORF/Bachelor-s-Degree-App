package com.ikram.pfe.handlers;

import com.ikram.pfe.exceptions.ExceptionRepresentation;
import com.ikram.pfe.exceptions.ObjectValidationException;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ObjectValidationException.class)
  public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception) {
    ExceptionRepresentation exceptionRepresentation = ExceptionRepresentation.builder()
        .errorMessage("Object not valid Exception")
        .errorSource(exception.getValidationSource())
        .validationErrors(exception.getViolations())
        .build();
    return ResponseEntity.badRequest().body(exceptionRepresentation);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ExceptionRepresentation> handleException() {
    ExceptionRepresentation exceptionRepresentation = ExceptionRepresentation.builder()
        .errorMessage("Object not found Exception")
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionRepresentation);
  }

  @ExceptionHandler({IllegalStateException.class, UsernameNotFoundException.class})
  public ResponseEntity<ExceptionRepresentation> handleException(IllegalStateException exception) {
    ExceptionRepresentation exceptionRepresentation = ExceptionRepresentation.builder()
        .errorMessage(exception.getMessage())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionRepresentation);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ExceptionRepresentation> handleException(BadCredentialsException exception) {
    ExceptionRepresentation exceptionRepresentation = ExceptionRepresentation.builder()
        .errorMessage("Login and / or password is incorrect")
        .build();
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionRepresentation);
  }
}
