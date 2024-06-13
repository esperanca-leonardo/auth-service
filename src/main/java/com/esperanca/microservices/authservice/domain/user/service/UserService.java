package com.esperanca.microservices.authservice.domain.user.service;

import com.esperanca.microservices.authservice.domain.user.entity.User;
import com.esperanca.microservices.authservice.domain.user.exception.UsernameAlreadyExistsException;

public interface UserService {

  User save(final User user);

  void existsByUsername(final String username)
      throws UsernameAlreadyExistsException;
}
