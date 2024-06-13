package com.esperanca.microservices.authservice.domain.user.repository;

import com.esperanca.microservices.authservice.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByUsername(String username);
  UserDetails findByUsername(String username);
}
