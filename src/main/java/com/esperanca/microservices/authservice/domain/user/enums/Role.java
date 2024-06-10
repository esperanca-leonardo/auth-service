package com.esperanca.microservices.authservice.domain.user.enums;

import lombok.Getter;

@Getter
public enum Role {
  ADMIN("admin"),
  COMMON("common");

  private final String role;

  Role(String role) {
    this.role = role;
  }
}
