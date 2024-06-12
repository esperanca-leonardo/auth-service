package com.esperanca.microservices.authservice.domain.user.dtos;

import com.esperanca.microservices.authservice.domain.user.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInDto {

  @Schema(example = "user")
  @NotBlank(message = "username cannot be blank")
  private String username;

  @Schema(example = "123")
  @NotBlank(message = "password cannot be blank")
  private String password;

  @Schema(example = "ADMIN")
  @NotBlank(message = "role cannot be blank")
  private Role role;
}
