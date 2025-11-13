package com.example.rve.global.security.auth;

import com.example.rve.domain.user.User;
import com.example.rve.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
    User user =userRepository.findByAccountId(accountId);
    return new CustomUserDetails(user);
  }
}
