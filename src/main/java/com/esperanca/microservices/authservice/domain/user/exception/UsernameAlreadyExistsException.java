package com.esperanca.microservices.authservice.domain.user.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

  private static final String MESSAGE = "Username %s already exists";

  public UsernameAlreadyExistsException(String username) {
    super(String.format(MESSAGE, username));
  }
}
