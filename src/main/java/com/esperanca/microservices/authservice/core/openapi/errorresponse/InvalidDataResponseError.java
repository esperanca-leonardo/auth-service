package com.esperanca.microservices.authservice.core.openapi.errorresponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class InvalidDataResponseError {

  @Schema(example = "https://authservice.com.br/invalid-data")
  private String type;

  @Schema(example = "Invalid data")
  private String title;

  @Schema(example = "400")
  private int status;

  @Schema(example = "One or more fields are invalid. Please fill in the " +
      "correct information and try again."
  )
  private String detail;

  @Schema(example = "/api/auth/sign-in")
  private String instance;

  @Schema(example = "2024-06-12T21:29:00Z")
  private String timestamp;

  @Schema(description = "list of invalid fields")
  private List<Field> fields;

  @Getter
  public static class Field {

    @Schema(example = "password")
    private String name;

    @Schema(example = "password cannot be blank")
    private String message;
  }
}
