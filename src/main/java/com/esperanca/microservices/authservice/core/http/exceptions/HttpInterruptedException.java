package com.esperanca.microservices.authservice.core.http.exceptions;

public class HttpInterruptedException extends RuntimeException {

  public HttpInterruptedException(String message, Throwable cause) {
    super(message, cause);
  }
}
