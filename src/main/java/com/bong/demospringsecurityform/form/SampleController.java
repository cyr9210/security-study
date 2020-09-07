package com.bong.demospringsecurityform.form;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SampleController {

  private final SampleService sampleService;

  @GetMapping("/")
  public String index(Model model, Principal principal) {
    if (principal == null) {
      model.addAttribute("message", "Hello Spring Security");
    } else {
      model.addAttribute("message", "Hello " + principal.getName());
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


}
