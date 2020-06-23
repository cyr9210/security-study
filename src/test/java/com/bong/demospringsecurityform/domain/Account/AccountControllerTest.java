package com.bong.demospringsecurityform.domain.Account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bong.demospringsecurityform.config.WithYrchoiUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void index_anonymous() throws Exception {
    mockMvc.perform(get("/"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void index_user() throws Exception {
    mockMvc.perform(get("/")
        .with(user("yrchoi").roles("USER"))
    )
        .andDo(print())
        .andExpect(status().isOk());
  }

  @WithMockUser(username = "yrchoi", roles = "ADMIN")
  @Test
  public void admin_admin() throws Exception {
    mockMvc.perform(get("/admin"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @WithYrchoiUser
  @Test
  public void admin_user() throws Exception {
    mockMvc.perform(get("/admin"))
        .andDo(print())
        .andExpect(status().isForbidden());
  }
}