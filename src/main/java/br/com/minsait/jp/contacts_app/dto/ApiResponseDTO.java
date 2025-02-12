package br.com.minsait.jp.contacts_app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.minsait.jp.contacts_app.enums.ResponseType;

@JsonInclude(JsonInclude.Include.NON_NULL) // Para que o body não seja retornado quando for nulo, em caso de erro.
public record ApiResponseDTO<T>(String message, T body, ResponseType responseType) {

  public static <T> ApiResponseDTO<T> success(String message, T body) {
    return new ApiResponseDTO<T>(message, body, ResponseType.SUCCESS);
  }

  public static <T> ApiResponseDTO<T> error(String message) {
    return new ApiResponseDTO<T>(message, null, ResponseType.ERROR);
  }

}