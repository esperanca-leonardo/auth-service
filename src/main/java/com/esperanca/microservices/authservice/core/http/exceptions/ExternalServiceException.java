package com.esperanca.microservices.authservice.core.http.exceptions;

public class ExternalServiceException extends RuntimeException {
  private static final String MESSAGE = "Request failed with status code: %d";

  public ExternalServiceException(int httpStatus) {
    super(String.format(MESSAGE, httpStatus));
  }
}
