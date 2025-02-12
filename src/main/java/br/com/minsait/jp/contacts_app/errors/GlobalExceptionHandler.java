package br.com.minsait.jp.contacts_app.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.minsait.jp.contacts_app.common.ApiResponse;
import br.com.minsait.jp.contacts_app.enums.ResponseType;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<?>> handleAll(Exception e) {
    logger.error("Unexpected error occurred: Exception. " + e.getMessage());
    ApiResponse<?> response = new ApiResponse<>();
    response.setMessage("Houve um erro inesperado.");
    response.setType(ResponseType.ERROR);
    response.setBody(null);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse<?>> handleIlegalArguments(IllegalArgumentException e) {
    logger.error("Unexpected error occurred: IllegalArgumentException. " + e.getMessage());
    ApiResponse<?> response = new ApiResponse<>();
    response.setMessage("Houve um erro inesperado. " + e.getMessage());
    response.setType(ResponseType.ERROR);
    response.setBody(null);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
    logger.error("Unexpected error occurred: MethodArgumentNotValidException. " + e.getMessage());

    ApiResponse<?> response = new ApiResponse<>();
    StringBuilder errorMessage = new StringBuilder();

    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      errorMessage.append(fieldError.getDefaultMessage()).append("; ");
    }

    response.setMessage("Houve um erro inesperado. " + errorMessage);
    response.setType(ResponseType.ERROR);
    response.setBody(null);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiResponse<?>> handleEntityNotFound(EntityNotFoundException e) {
    logger.error("Unexpected error occurred: EntityNotFoundException. " + e.getMessage());

    ApiResponse<?> response = new ApiResponse<>();
    response.setMessage("Houve um erro inesperado. " + e.getMessage());
    response.setType(ResponseType.ERROR);
    response.setBody(null);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
  }

}