package com.esperanca.microservices.authservice.domain.token.service;

public interface TokenService {

  String generateToken(final String username);
}
