package com.esperanca.microservices.authservice.core.exception.problemdetail.helper;

import com.esperanca.microservices.authservice.core.exception.problemdetail.fielderros.Field;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Instant;
import java.util.List;

public interface ProblemDetailHelper {

  Instant createTimestampWithZeroNanos();
  Field mapToField(FieldError fieldError);
  List<Field> extractFieldErrors(MethodArgumentNotValidException exception);
}
