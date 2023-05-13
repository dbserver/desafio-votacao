package com.db.polling.domain.exception;


public class NotFoundException extends RuntimeException {

  private final String code;
  public NotFoundException(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
