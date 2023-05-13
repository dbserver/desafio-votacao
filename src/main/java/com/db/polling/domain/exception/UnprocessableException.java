package com.db.polling.domain.exception;


public class UnprocessableException extends RuntimeException {

  private final String code;
  public UnprocessableException(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
