package com.esperanca.microservices.authservice.domain.auth.service;

import com.esperanca.microservices.authservice.domain.auth.model.Token;
import com.esperanca.microservices.authservice.domain.token.service.TokenService;
import com.esperanca.microservices.authservice.domain.user.dtos.LoginDto;
import com.esperanca.microservices.authservice.domain.user.dtos.SignInDto;
import com.esperanca.microservices.authservice.domain.user.entity.User;
import com.esperanca.microservices.authservice.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserService userService;
  private final TokenService tokenService;
  private final BCryptPasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @Override
  public User signIn(SignInDto signInDto) {
    final String username = signInDto.getUsername();

    this.userService.existsByUsername(username);

    final String passwordEncoded =
        this.passwordEncoder.encode(signInDto.getPassword());

    final User user = User.builder()
        .username(username)
        .password(passwordEncoded)
        .role(signInDto.getRole())
        .build();

    return this.userService.save(user);
  }

  @Override
  public Token login(LoginDto loginDto) {
    final var authenticationToken = new UsernamePasswordAuthenticationToken(
        loginDto.getUsername(), loginDto.getPassword()
    );

    final Authentication authenticate =
        this.authenticationManager.authenticate(authenticationToken);

    final User user = (User) authenticate.getPrincipal();
    final String username = user.getUsername();
    final String token = this.tokenService.generateToken(username);

    return new Token(token);
  }
}
