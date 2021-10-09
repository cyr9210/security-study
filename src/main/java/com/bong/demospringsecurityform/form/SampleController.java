package com.bong.demospringsecurityform.form;

import com.bong.demospringsecurityform.common.CurrentUser;
import com.bong.demospringsecurityform.common.SecurityLogger;
import com.bong.demospringsecurityform.domain.Account.Account;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class SampleController {

  private final SampleService sampleService;

  @GetMapping("/")
  public String index(Model model, @CurrentUser Account account) {
    if (account == null) {
      model.addAttribute("message", "Hello Spring Security");
    } else {
      model.addAttribute("message", "Hello " + account.getUsername());
    }
    return "index";
  }

  @GetMapping("/info")
  public String info(Model model) {
    model.addAttribute("message", "Hello Spring Security");
    return "info";
  }

  @GetMapping("/dashboard")
  public String dashboard(Model model, Principal principal) {
    model.addAttribute("message", "Hello" + principal.getName());
    sampleService.dashBoard();
    return "dashboard";
  }

  @GetMapping("/admin")
  public String admin(Model model, Principal principal) {
    model.addAttribute("message", "Hello " + principal.getName());
    return "admin";
  }

  @GetMapping("async-handler")
  @ResponseBody
  public String asyncHandler() {
    SecurityLogger.log("MVC before async service.");
    sampleService.asyncService();
    SecurityLogger.log("MVC after async service.");
    return "Async Service";
  }

}
