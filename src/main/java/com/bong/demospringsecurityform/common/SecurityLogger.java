package com.bong.demospringsecurityform.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityLogger {

  public static void log(String message) {
    log.info("message:{}", message);
    log.info("threadName:{}", Thread.currentThread().getName());
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    log.info("principal:{}", principal);
  }

}
