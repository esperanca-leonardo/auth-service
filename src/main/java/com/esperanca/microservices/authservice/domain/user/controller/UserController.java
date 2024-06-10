package com.esperanca.microservices.authservice.domain.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserDetailsService userDetailsService;

  @GetMapping
  @ResponseStatus(OK)
  public UserDetails userDetails(@RequestParam String username) {
    return this.userDetailsService.loadUserByUsername(username);
  }
}
