package com.esperanca.microservices.authservice.domain.token.service;

import java.io.IOException;

public interface TokenService {

  String generateToken(String username) throws IOException, InterruptedException;
}
