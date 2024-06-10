package com.esperanca.microservices.authservice.domain.user.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

  @NotBlank(message = "username cannot be blank")
  private String username;

  @NotBlank(message = "password cannot be blank")
  private String password;
}
