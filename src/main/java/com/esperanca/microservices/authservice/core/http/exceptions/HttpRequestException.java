package com.esperanca.microservices.authservice.core.http.exceptions;

public class HttpRequestException extends RuntimeException {

  public HttpRequestException(String message, Throwable cause) {
    super(message, cause);
  }
}
