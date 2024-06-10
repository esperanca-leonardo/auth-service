package com.esperanca.microservices.authservice.domain.token.client;

public interface TokenClient {

  String generateToken(String username) throws RuntimeException;
}
