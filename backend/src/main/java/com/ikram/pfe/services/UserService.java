package com.ikram.pfe.services;

import com.ikram.pfe.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return repository.findByEmail(email)
        .orElseThrow(() ->
            new UsernameNotFoundException(
                String.format("No user having the email %s was found", email)
            )
        );
  }
}
