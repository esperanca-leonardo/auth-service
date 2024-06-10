package com.esperanca.microservices.authservice.domain.user.dtos;

import com.esperanca.microservices.authservice.domain.user.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInDto {

  @NotBlank(message = "username cannot be blank")
  private String username;

  @NotBlank(message = "password cannot be blank")
  private String password;

  private Role role;
}
