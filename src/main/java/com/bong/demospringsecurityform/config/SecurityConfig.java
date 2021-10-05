package com.bong.demospringsecurityform.config;

import com.bong.demospringsecurityform.common.LoggingFilter;
import com.bong.demospringsecurityform.domain.Account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final AccountService accountService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .mvcMatchers("/", "/info", "/account/**", "/signup").permitAll()
        .mvcMatchers("/admin").hasRole("ADMIN")
        .mvcMatchers("/users").hasRole("USER")
        .anyRequest().authenticated()
        .expressionHandler(expressionHandler())
        .and()
        .formLogin()
        .and()
        .httpBasic();

    SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);

    http.logout()
        .logoutUrl("/my/logout")
        .logoutSuccessUrl("/")
//        .logoutRequestMatcher()
        .invalidateHttpSession(true)
        .deleteCookies()
//        .addLogoutHandler()
//        .logoutSuccessHandler()
    ;

    http.formLogin()
        .usernameParameter("my-username")
        .passwordParameter("my-password")
        .loginProcessingUrl("/my-login")
        .loginPage("/signin").permitAll();

//    http.sessionManagement()
//          .sessionFixation()
//            .none()
//          .invalidSessionUrl("/")
//          .maximumSessions(1)
//            .maxSessionsPreventsLogin(false)
//            .expiredUrl("/").and()
//          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.exceptionHandling()
//        .accessDeniedPage("/access-denied")
        .accessDeniedHandler((request, response, accessDeniedException) -> {
          UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          String username = principal.getUsername();
          System.out.println(username + " is denied to access " + request.getRequestURI());
          response.sendRedirect("/access-denied");
        });

    http.addFilterBefore(new LoggingFilter(), WebAsyncManagerIntegrationFilter.class);
  }

  private DefaultWebSecurityExpressionHandler expressionHandler() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

    DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
    handler.setRoleHierarchy(roleHierarchy);
    return handler;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(accountService);
  }
}
