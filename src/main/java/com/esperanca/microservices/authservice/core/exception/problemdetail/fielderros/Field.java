package com.esperanca.microservices.authservice.core.exception.problemdetail.fielderros;

import lombok.Builder;

@Builder
public record Field(String name, String message) { }
