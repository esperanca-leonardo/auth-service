package com.esperanca.microservices.authservice.domain.token.client;

import com.esperanca.microservices.authservice.core.http.helper.HttpHelper;
import com.esperanca.microservices.authservice.core.json.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class TokenClientImpl implements TokenClient {

  @Autowired
  private JsonHelper jsonHelper;

  @Autowired
  private HttpHelper httpHelper;

  @Value("${token.service.host}")
  private String tokenServiceHost;

  @Value("${token.service.port}")
  private String tokenServicePort;

  @Override
  public String getTokenFromService(final String username) {
    final URI uri = this.httpHelper.createUri(
        this.tokenServiceHost, this.tokenServicePort,
        "api/tokens/generate", "username", username
    );
    final HttpRequest request = this.httpHelper.buildRequest(uri);
    final HttpResponse<String> response = this.httpHelper.sendRequest(request);

    this.httpHelper.isNotSuccessfulResponse(response);

    return this.jsonHelper.getValueFromKey(response.body(), "token");
  }
}
