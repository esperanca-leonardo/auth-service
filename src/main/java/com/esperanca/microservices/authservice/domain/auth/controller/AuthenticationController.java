package com.esperanca.microservices.authservice.domain.auth.controller;

import com.esperanca.microservices.authservice.domain.auth.model.Token;
import com.esperanca.microservices.authservice.domain.auth.controller.openapi.AuthenticationControllerOpenApi;
import com.esperanca.microservices.authservice.domain.auth.service.AuthenticationService;
import com.esperanca.microservices.authservice.domain.user.dtos.LoginDto;
import com.esperanca.microservices.authservice.domain.user.dtos.SignInDto;
import com.esperanca.microservices.authservice.domain.user.entity.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController implements AuthenticationControllerOpenApi {

  private final AuthenticationService authenticationService;

  @Override
  @ResponseStatus(CREATED)
  @PostMapping("/sign-in")
  public User signIn(@RequestBody @Valid SignInDto signInDto) {
    return this.authenticationService.signIn(signInDto);
  }

  @Override
  @ResponseStatus(OK)
  @PostMapping("/login")
  public Token login(@RequestBody @Valid LoginDto loginDto) {
    return this.authenticationService.login(loginDto);
  }
}
