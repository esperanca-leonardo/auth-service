package com.esperanca.microservices.authservice.domain.token.client;

public interface TokenClient {

  String generateToken(final String username);
}
