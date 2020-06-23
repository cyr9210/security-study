package com.bong.demospringsecurityform.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithMockUser;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "yrchoi", roles = "USER")
public @interface WithYrchoiUser {

}
