package com.esperanca.microservices.authservice.domain.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;

public record Token(
    @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBdXR" +
        "oVG9rZW5TZXJ2aWNlIiwic3ViIjoiZXNwZXJhbmNhIiwiZXhwIjoxNzE4MzA4NDc5f" +
        "Q.-4sNENF5Es9i5bQI8GGNqyCGAd96be9p_ZoDJKk8_jY"
    )
    String token)
{ }
