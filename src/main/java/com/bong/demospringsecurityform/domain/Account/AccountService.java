package com.bong.demospringsecurityform.domain.Account;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AccountService implements UserDetailsService {

  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Account> byUsername = accountRepository.findByUsername(username);
    Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(String.format("%s is not founded!", username)));

    return User.builder()
        .username(account.getUsername())
        .password(account.getPassword())
        .roles(account.getRole())
        .build();
  }

  public Account create(Account account) {
    account.encodePassword(passwordEncoder);
    return accountRepository.save(account);
  }
}
