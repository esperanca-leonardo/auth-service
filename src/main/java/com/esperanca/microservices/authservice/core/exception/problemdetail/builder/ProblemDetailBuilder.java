package com.esperanca.microservices.authservice.core.exception.problemdetail.builder;

import com.esperanca.microservices.authservice.core.exception.problemdetail.fielderros.Field;
import com.esperanca.microservices.authservice.core.exception.problemdetail.enums.ProblemDetailType;
import com.esperanca.microservices.authservice.core.exception.problemdetail.helper.ProblemDetailHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@Component
@AllArgsConstructor
public class ProblemDetailBuilder {

  private final ProblemDetailHelper problemDetailHelper;

  private ProblemDetail createProblemDetail(ProblemDetailType problemDetailType,
      String detail) {

    final URI type = problemDetailType.getType();
    final String title = problemDetailType.getTitle();
    final HttpStatus status = problemDetailType.getStatus();

    final ProblemDetail problemDetail = forStatusAndDetail(status, detail);

    problemDetail.setType(type);
    problemDetail.setTitle(title);
    problemDetail.setProperty("timestamp",
        problemDetailHelper.createTimestampWithZeroNanos()
    );

    return problemDetail;
  }

  public ProblemDetail buildProblemDetail(ProblemDetailType problemDetailType,
      String detail) {

    return this.createProblemDetail(problemDetailType, detail);
  }

  public ProblemDetail buildFieldErrorsProblemDetail(
      ProblemDetailType problemDetailType,
      MethodArgumentNotValidException exception) {

    final var detail = "One or more fields are invalid. Please fill in " +
        "the correct information and try again.";

    final ProblemDetail problemDetail =
        this.buildProblemDetail(problemDetailType, detail);

    final List<Field> fieldErrors =
        this.problemDetailHelper.extractFieldErrors(exception);

    problemDetail.setProperty("fields", fieldErrors);

    return problemDetail;
  }

  public ProblemDetail buildBadCredentialsProblemDetail(
      ProblemDetailType problemDetailType) {

    final var detail = "Invalid credentials. Please check your username " +
        "and password and try again.";

    return this.buildProblemDetail(problemDetailType, detail);
  }
}

