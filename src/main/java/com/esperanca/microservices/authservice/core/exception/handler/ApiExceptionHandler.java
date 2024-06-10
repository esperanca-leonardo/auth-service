package com.esperanca.microservices.authservice.core.exception.handler;

import com.esperanca.microservices.authservice.core.exception.problemdetail.builder.ProblemDetailBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.esperanca.microservices.authservice.core.exception.problemdetail.enums.ProblemDetailType.*;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private final ProblemDetailBuilder problemDetailBuilder;

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleUsernameNotFoundException(
      UsernameNotFoundException exception) {

    final ProblemDetail problemDetail = this.problemDetailBuilder
        .buildProblemDetail(USER_NOT_FOUND, exception.getMessage());

    return new ResponseEntity<>(problemDetail, USER_NOT_FOUND.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {

    final ProblemDetail problemDetail = this.problemDetailBuilder
        .buildFieldErrorsProblemDetail(INVALID_DATA, ex);

    return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ProblemDetail> handleBadCredentialsException(
      BadCredentialsException exception) {

    final ProblemDetail problemDetail = this.problemDetailBuilder
        .buildBadCredentialsProblemDetail(BAD_CREDENTIALS);

    return new ResponseEntity<>(problemDetail, BAD_CREDENTIALS.getStatus());
  }
}
