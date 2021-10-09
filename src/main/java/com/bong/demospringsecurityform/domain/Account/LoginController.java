package com.bong.demospringsecurityform.domain.Account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

  @GetMapping("/signin")
  public String loginForm() {
    return "sign-in";
  }

  @GetMapping("/logout")
  public String logoutPage() {
    return "logout";
  }

}
