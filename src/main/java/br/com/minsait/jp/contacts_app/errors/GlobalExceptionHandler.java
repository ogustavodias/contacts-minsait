package br.com.minsait.jp.contacts_app.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.minsait.jp.contacts_app.dto.ApiResponseDTO;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponseDTO<?>> handleAll(Exception e) {
    logger.error("Unexpected error occurred: Exception. " + e.getMessage());
    ApiResponseDTO<?> response = ApiResponseDTO.error("Houve um erro inesperado.");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponseDTO<?>> handleIlegalArguments(IllegalArgumentException e) {
    logger.error("Unexpected error occurred: IllegalArgumentException. " + e.getMessage());
    ApiResponseDTO<?> response = ApiResponseDTO.error("Houve um erro inesperado. " + e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponseDTO<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
    logger.error("Unexpected error occurred: MethodArgumentNotValidException. " + e.getMessage());

    StringBuilder errorMessage = new StringBuilder();

    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      errorMessage.append(fieldError.getDefaultMessage()).append("; ");
    }

    ApiResponseDTO<?> response = ApiResponseDTO.error("Houve um erro inesperado. " + errorMessage);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiResponseDTO<?>> handleEntityNotFound(EntityNotFoundException e) {
    logger.error("Unexpected error occurred: EntityNotFoundException. " + e.getMessage());
    ApiResponseDTO<?> response = ApiResponseDTO.error("Houve um erro inesperado. " + e.getMessage());
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
  }

}