package com.esperanca.microservices.authservice.domain.auth.controller;

import com.esperanca.microservices.authservice.domain.token.service.TokenService;
import com.esperanca.microservices.authservice.domain.user.dtos.LoginDto;
import com.esperanca.microservices.authservice.domain.user.dtos.SignInDto;
import com.esperanca.microservices.authservice.domain.user.entity.User;
import com.esperanca.microservices.authservice.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

  private final TokenService tokenService;
  private final UserRepository repository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @PostMapping("/sign-in")
  public ResponseEntity<User> signIn(@RequestBody @Valid SignInDto signInDto) {

    if (repository.findByUsername(signInDto.getUsername()) != null) {
      return ResponseEntity.badRequest().build();
    }

    final String passwordEncoded = this.passwordEncoder.encode(signInDto.getPassword());
    User user = new User(signInDto.getUsername(), passwordEncoded, signInDto.getRole());

    user = this.repository.save(user);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto)
      throws IOException, InterruptedException {

    final var authenticationToken = new UsernamePasswordAuthenticationToken(
        loginDto.getUsername(), loginDto.getPassword()
    );

    final Authentication authenticate =
        this.authenticationManager.authenticate(authenticationToken);

    final User user = (User) authenticate.getPrincipal();
    final String username = user.getUsername();

    final String token = this.tokenService.generateToken(username);
    return ResponseEntity.ok(token);
  }
}
