package com.esperanca.microservices.authservice.domain.auth.service;

import com.esperanca.microservices.authservice.domain.auth.model.Token;
import com.esperanca.microservices.authservice.domain.user.dtos.LoginDto;
import com.esperanca.microservices.authservice.domain.user.dtos.SignInDto;
import com.esperanca.microservices.authservice.domain.user.entity.User;

public interface AuthenticationService {

  Token login(LoginDto loginDto);

  User signIn(SignInDto signInDto);
}
