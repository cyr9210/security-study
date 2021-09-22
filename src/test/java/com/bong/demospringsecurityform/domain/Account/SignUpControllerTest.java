package com.bong.demospringsecurityform.domain.Account;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class SignUpControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void signUpForm() throws Exception {
    mockMvc.perform(get("/signup"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("_csrf")));
  }

  @Test
  void processSignup() throws Exception {
    mockMvc.perform(post("/signup")
            .param("username", "yrchoi")
            .param("password", "123")
            .with(csrf())
        )
        .andDo(print())
        .andExpect(status().is3xxRedirection());
  }
}