package com.esperanca.microservices.authservice.domain.token.client;

public interface TokenClient {

  String getTokenFromService(final String username);
}
