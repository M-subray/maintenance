package zerobase.maintenance.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import zerobase.maintenance.dto.SignupDto;
import zerobase.maintenance.service.SignupService;

@SpringBootTest
@AutoConfigureMockMvc
class SignupControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private SignupService signupService;

  @Test
  @DisplayName("회원 가입 성공 - USER")
  void signupSuccessForUser() throws Exception {
    // given
    SignupDto.Request signupDto = SignupDto.Request.builder()
        .username("username")
        .password("password")
        .name("testName")
        .address("testAddress")
        .mobile("010-1234-5678")
        .mail("test@mail.com")
        .build();

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonContent = objectMapper.writeValueAsString(signupDto);

    // when
    mockMvc.perform(post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent)
            .param("role", "ROLE_USER"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "MANAGER", roles = {"MANAGER"})
  @DisplayName("회원 가입 성공 - PARTNER_IN_OFFICE")
  void signupSuccessForPartnerInOffice() throws Exception {
    // given
    SignupDto.Request signupDto = SignupDto.Request.builder()
        .username("username")
        .password("password")
        .name("testName")
        .address("testAddress")
        .mobile("010-1234-5678")
        .mail("test@mail.com")
        .build();

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonContent = objectMapper.writeValueAsString(signupDto);

    // when
    mockMvc.perform(post("/signup/partner/office")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent)
            .param("role", "ROLE_PARTNER_IN_OFFICE"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "MANAGER", roles = {"MANAGER"})
  @DisplayName("회원 가입 성공 - PARTNER_ON_FIELD")
  void signupSuccessForPartnerOnField() throws Exception {
    // given
    SignupDto.Request signupDto = SignupDto.Request.builder()
        .username("username")
        .password("password")
        .name("testName")
        .address("testAddress")
        .mobile("010-1234-5678")
        .mail("test@mail.com")
        .build();

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonContent = objectMapper.writeValueAsString(signupDto);

    // when
    mockMvc.perform(post("/signup/partner/office")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent)
            .param("role", "ROLE_PARTNER_ON_FIELD"))
        .andExpect(status().isOk());
  }
}
