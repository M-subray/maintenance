package zerobase.maintenance.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import zerobase.maintenance.domain.Account;
import zerobase.maintenance.dto.SigninDto;
import zerobase.maintenance.exception.AccountException;
import zerobase.maintenance.security.TokenProvider;
import zerobase.maintenance.service.SigninService;
import zerobase.maintenance.type.ErrorCode;

@WebMvcTest(SigninController.class)
class SigninControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SigninService signinService;

  @MockBean
  private TokenProvider tokenProvider;

  @Test
  @DisplayName("로그인 성공")
  void successSignin() throws Exception {
    // given
    SigninDto signinDto = SigninDto.builder()
        .username("testUser")
        .password("testPassword")
        .build();

    String mockToken = "mockToken";
    Account testAccount = Account.builder()
        .username(signinDto.getUsername())
        .password("hashedPassword")
        .build();

    // when
    when(signinService.signin(signinDto.getUsername(), signinDto.getPassword()))
        .thenReturn(testAccount);
    when(tokenProvider.generateToken(testAccount.getUsername(), testAccount.getRole()))
        .thenReturn(mockToken);

    // then
    mockMvc.perform(post("/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":\"" + signinDto.getUsername()
                + "\",\"password\":\"" + signinDto.getPassword() + "\"}"))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("로그인 실패 - username 존재하지 않음")
  void failSigninForWrongUsername() throws Exception {
    // given
    String usernameNotFound = "usernameNotFound";
    SigninDto signinDto = SigninDto.builder()
        .username("testUser")
        .password("testPassword")
        .build();

    // when
    when(signinService.signin(usernameNotFound, signinDto.getPassword()))
        .thenThrow(new AccountException(ErrorCode.USERNAME_NOT_FOUND));

    // then
    mockMvc.perform(post("/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":\"" + usernameNotFound
                + "\",\"password\":\"" + signinDto.getPassword() + "\"}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("로그인 실패 - username 이 null 인 경우")
  void failSigninForEmptyUsername() throws Exception {
    // given
    SigninDto signinDto = SigninDto.builder()
        .username(null)
        .password("testPassword")
        .build();

    // then
    mockMvc.perform(post("/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":null,\"password\":\""
                + signinDto.getPassword() + "\"}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("로그인 실패 - password 틀림")
  void failSigninForWrongPassword() throws Exception {
    // given
    String wrongPassword = "wrongPassword";
    SigninDto signinDto = SigninDto.builder()
        .username("testUser")
        .password("testPassword")
        .build();

    // when
    when(signinService.signin(signinDto.getUsername(), wrongPassword))
        .thenThrow(new AccountException(ErrorCode.WRONG_PASSWORD));

    // then
    mockMvc.perform(post("/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":\"" + signinDto.getUsername()
                + "\",\"password\":\"" + wrongPassword + "\"}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("로그인 실패 - password 가 null 인 경우")
  void failSigninForEmptyPassword() throws Exception {
    // given
    SigninDto signinDto = SigninDto.builder()
        .username("testUser")
        .password(null)
        .build();

    // when
    when(signinService.signin(signinDto.getUsername(), null))
        .thenThrow(new AccountException(ErrorCode.WRONG_PASSWORD));

    // then
    mockMvc.perform(post("/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":\"" + signinDto.getUsername()
                + "\",\"password\":\"" + null + "\"}"))
        .andExpect(status().isBadRequest());
  }
}