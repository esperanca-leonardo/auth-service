package com.esperanca.microservices.authservice.domain.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

  @Schema(example = "user")
  @NotBlank(message = "username cannot be blank")
  private String username;

  @Schema(example = "123")
  @NotBlank(message = "password cannot be blank")
  private String password;
}
