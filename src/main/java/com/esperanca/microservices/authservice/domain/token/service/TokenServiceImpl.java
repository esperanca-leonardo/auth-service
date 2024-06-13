package com.esperanca.microservices.authservice.domain.token.service;

import com.esperanca.microservices.authservice.domain.token.client.TokenClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

  private final TokenClient tokenClient;

  @Override
  public String generateToken(final String username) {
    return this.tokenClient.getTokenFromService(username);
  }
}
