package com.esperanca.microservices.authservice.core.exception.problemdetail.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum ProblemDetailType {
  INVALID_DATA("/invalid-data", "Invalid data", BAD_REQUEST),
  USER_NOT_FOUND("/user-not-found", "User not found", NOT_FOUND),
  BAD_CREDENTIALS("/bad-credentials", "Bad credentials", BAD_REQUEST),
  MISSING_REQUEST_PARAMETER("/missing-request-parameter", "Missing request parameter", BAD_REQUEST);

  private final URI type;
  private final String title;
  private final HttpStatus status;

  ProblemDetailType(String type, String title, HttpStatus status) {
    this.title = title;
    this.status = status;
    this.type = URI.create("https://authservice.com.br" + type);
  }
}
