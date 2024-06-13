package com.esperanca.microservices.authservice.core.http.helper;

import com.esperanca.microservices.authservice.core.http.exceptions.ExternalServiceException;
import com.esperanca.microservices.authservice.core.http.exceptions.HttpInterruptedException;
import com.esperanca.microservices.authservice.core.http.exceptions.HttpRequestException;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface HttpHelper {

  URI createUri(String host, String port, String endpoint, String key,
      String value
  );

  HttpRequest buildRequest(URI uri);

  HttpResponse<String> sendRequest(HttpRequest request)
      throws HttpRequestException, HttpInterruptedException;

  void isNotSuccessfulResponse(HttpResponse<String> response)
      throws ExternalServiceException;
}
