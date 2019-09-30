package com.cliente.exceptionMapper;

import com.cliente.exception.BadRequestException;
import com.cliente.exception.NotFoundException;
import com.cliente.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClienteControllerAdvice {

  private ResponseEntity<?> error(HttpStatus status, Response e) {
    return ResponseEntity.status(status).body(e);
  }

  @ExceptionHandler({BadRequestException.class})
  public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
    return error(HttpStatus.BAD_REQUEST, e.getResponse());
  }

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
    return error(HttpStatus.NOT_FOUND, e.getResponse());
  }
}
