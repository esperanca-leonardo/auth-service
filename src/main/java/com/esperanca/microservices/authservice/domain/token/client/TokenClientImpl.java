package com.esperanca.microservices.authservice.domain.token.client;

import com.esperanca.microservices.authservice.core.http.helper.HttpHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class TokenClientImpl implements TokenClient {

  @Autowired
  private HttpHelper httpHelper;

  @Value("${token.service.host}")
  private String tokenServiceHost;

  @Value("${token.service.port}")
  private String tokenServicePort;

  @Override
  public String generateToken(String username) throws RuntimeException {
    final URI uri = this.httpHelper.createUri(
        this.tokenServiceHost, this.tokenServicePort,
        "api/tokens/generate", "username", username
    );

    final HttpRequest request = this.httpHelper.buildRequest(uri);
    final HttpResponse<String> response = this.httpHelper.sendRequest(request);

    if (response.statusCode() == 200) {
      return response.body();
    }
    else {
      throw new RuntimeException("Error generating token: " + response.body());
    }
  }
}
