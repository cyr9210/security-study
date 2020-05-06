package com.bong.demospringsecurityform.domain.Account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {

  private final AccountService accountService;

  @GetMapping("/account/{role}/{username}/{password}")
  public Account create(@ModelAttribute Account account) {
    return accountService.create(account);
  }

}
