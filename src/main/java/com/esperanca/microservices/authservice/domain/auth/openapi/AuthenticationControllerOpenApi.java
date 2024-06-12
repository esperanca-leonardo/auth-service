package com.esperanca.microservices.authservice.domain.auth.openapi;

import com.esperanca.microservices.authservice.domain.user.dtos.LoginDto;
import com.esperanca.microservices.authservice.domain.user.dtos.SignInDto;
import com.esperanca.microservices.authservice.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Tag(
    name = "Authentication",
    description = "This controller handles user authentication operations " +
        "such as sign-in and login."
)
public interface AuthenticationControllerOpenApi {

  @Operation(summary = "Register a new user")
  ResponseEntity<User> signIn(SignInDto signInDto);

  @Operation(summary = "Authenticate an existing user")
  ResponseEntity<String> login(LoginDto loginDto) throws IOException,
      InterruptedException;
}
