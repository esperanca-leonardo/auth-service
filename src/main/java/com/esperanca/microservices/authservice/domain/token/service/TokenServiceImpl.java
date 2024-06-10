package com.esperanca.microservices.authservice.domain.token.service;

import com.esperanca.microservices.authservice.domain.token.client.TokenClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

  private final TokenClient tokenClient;

  @Override
  public String generateToken(String username) throws IOException, InterruptedException {
    return this.tokenClient.generateToken(username);
  }
}
