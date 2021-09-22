package com.bong.demospringsecurityform.domain.Account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/signup")
@Controller
public class SignUpController {

  private final AccountService accountService;

  @GetMapping
  public String signUpForm(Model model) {
    model.addAttribute("account", new Account());
    return "signup";
  }

  @PostMapping
  public String processSignUp(@ModelAttribute Account account) {
    account.setRole("USER");
    accountService.create(account);
    return "redirect:/";
  }

}
