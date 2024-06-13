package com.esperanca.microservices.authservice.domain.user.service;

import com.esperanca.microservices.authservice.domain.user.entity.User;
import com.esperanca.microservices.authservice.domain.user.exception.UsernameAlreadyExistsException;
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
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(final String username)
      throws UsernameNotFoundException {

    final UserDetails userDetails = this.repository.findByUsername(username);

    if (isNull(userDetails)) {
      throw new UsernameNotFoundException(
          format("User not found with username: %s", username)
      );
    }
    return userDetails;
  }

  @Override
  public void existsByUsername(final String username)
      throws UsernameAlreadyExistsException {

    final boolean usernameExists = this.repository.existsByUsername(username);

    if (usernameExists) {
      throw new UsernameAlreadyExistsException(username);
    }
  }

  @Override
  public User save(final User user) {
    return this.repository.save(user);
  }
}
