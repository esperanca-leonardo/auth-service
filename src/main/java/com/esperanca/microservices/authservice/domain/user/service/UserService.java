package com.esperanca.microservices.authservice.domain.user.service;

import com.esperanca.microservices.authservice.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final UserDetails userDetails = this.repository.findByUsername(username);

    if (isNull(userDetails)) {
      throw new UsernameNotFoundException(
          format("User not found with username: %s", username)
      );
    }
    return userDetails;
  }
}
