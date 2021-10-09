package com.bong.demospringsecurityform.form;

import com.bong.demospringsecurityform.common.SecurityLogger;
import javax.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SampleService {

  @Secured("ROLE_USER")
  @RolesAllowed("ROLE_USER")
  @PreAuthorize("hasRole('USER')")
  public void dashBoard() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    System.out.println("=================");
    System.out.println(authentication);
    System.out.println(userDetails.getUsername());
  }

  @Async
  public void asyncService() {
    SecurityLogger.log("async Service");
  }
}
