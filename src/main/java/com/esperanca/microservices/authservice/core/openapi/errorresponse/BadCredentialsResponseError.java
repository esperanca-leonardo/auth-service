package com.esperanca.microservices.authservice.core.openapi.errorresponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class BadCredentialsResponseError {

  @Schema(example = "https://authservice.com.br/bad-credentials")
  private String type;

  @Schema(example = "Bad credentials")
  private String title;

  @Schema(example = "400")
  private int status;

  @Schema(example = "Invalid credentials. Please check your username and " +
      "password and try again."
  )
  private String detail;

  @Schema(example = "/api/auth/login")
  private String instance;

  @Schema(example = "2024-06-12T21:29:00Z")
  private String timestamp;
}
