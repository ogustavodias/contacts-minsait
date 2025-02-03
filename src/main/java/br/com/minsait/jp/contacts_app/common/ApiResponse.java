package br.com.minsait.jp.contacts_app.common;

import br.com.minsait.jp.contacts_app.enums.ResponseType;

public class ApiResponse<T> {
  private String message;
  private T body;
  private ResponseType type;

  public ApiResponse() {
  }

  public ApiResponse(String message, T body, ResponseType type) {
    this.message = message;
    this.body = body;
    this.type = type;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getBody() {
    return body;
  }

  public void setBody(T body) {
    this.body = body;
  }

  public ResponseType getType() {
    return type;
  }

  public void setType(ResponseType type) {
    this.type = type;
  }

}
