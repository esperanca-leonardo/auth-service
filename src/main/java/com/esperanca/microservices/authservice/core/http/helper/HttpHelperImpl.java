package com.esperanca.microservices.authservice.core.http.helper;

import com.esperanca.microservices.authservice.core.http.exceptions.ExternalServiceException;
import com.esperanca.microservices.authservice.core.http.exceptions.HttpInterruptedException;
import com.esperanca.microservices.authservice.core.http.exceptions.HttpRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newHttpClient;
import static java.net.http.HttpRequest.newBuilder;
import static org.springframework.http.HttpStatus.OK;

@Component
public class HttpHelperImpl implements HttpHelper {

  private final HttpClient httpClient;

  public HttpHelperImpl() {
    this.httpClient = newHttpClient();
  }

  @Override
  public URI createUri(String host, String port, String endpoint, String key,
      String value) {

    final var uri = String.format("http://%s:%s/%s?%s=%s",
        host, port, endpoint, key, value
    );
    return URI.create(uri);
  }

  @Override
  public HttpRequest buildRequest(URI uri) {
    return newBuilder()
        .uri(uri)
        .POST(HttpRequest.BodyPublishers.noBody())
        .build();
  }

  @Override
  public HttpResponse<String> sendRequest(HttpRequest request)
      throws HttpRequestException, HttpInterruptedException {

    try {
      return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
    catch (IOException ioException) {
      throw new HttpRequestException(
          "Failed to send HTTP request", ioException
      );
    }
    catch (InterruptedException interruptedException) {
      throw new HttpInterruptedException(
          "HTTP request was interrupted", interruptedException
      );
    }
  }

  @Override
  public void isNotSuccessfulResponse(HttpResponse<String> response)
      throws ExternalServiceException {
    final boolean isNotOkResponse = response.statusCode() != OK.value();

    if (isNotOkResponse) {
      throw new ExternalServiceException(response.statusCode());
    }
  }
}
