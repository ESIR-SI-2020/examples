package fr.esir.jxc.examples.common.config;

import fr.esir.jxc.examples.common.exception.InvalidParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class GlobalErrorHandler {

  @ExceptionHandler(InvalidParameterException.class)
  public ResponseEntity<Object> handleInvalidParameterException(InvalidParameterException e) {
    return new ResponseEntity<>(HttpStatus.valueOf(400));
  }

}
