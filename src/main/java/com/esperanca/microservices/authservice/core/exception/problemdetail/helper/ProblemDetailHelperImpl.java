package com.esperanca.microservices.authservice.core.exception.problemdetail.helper;

import com.esperanca.microservices.authservice.core.exception.problemdetail.fielderros.Field;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

@Component
public class ProblemDetailHelperImpl implements ProblemDetailHelper {

  @Override
  public Instant createTimestampWithZeroNanos() {
    return OffsetDateTime.now().withNano(0).toInstant();
  }

  @Override
  public Field mapToField(FieldError fieldError) {
    return Field.builder()
        .name(fieldError.getField())
        .message(fieldError.getDefaultMessage())
        .build();
  }

  @Override
  public List<Field> extractFieldErrors(MethodArgumentNotValidException exception) {
    return exception.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(this::mapToField)
        .toList();
  }
}
