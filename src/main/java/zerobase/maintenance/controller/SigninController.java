package zerobase.maintenance.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.SigninDto;
import zerobase.maintenance.security.TokenProvider;
import zerobase.maintenance.service.SigninService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SigninController {
  private final SigninService signinService;
  private final TokenProvider tokenProvider;

  @PostMapping("/signin")
  public ResponseEntity<?> signin(@RequestBody @Valid SigninDto.Request signinDto) {
    signinService.signin(signinDto.getUsername(), signinDto.getPassword());
    String token =
        tokenProvider.generateToken(signinDto.getUsername(), signinDto.getRole());
    return ResponseEntity.ok().body("로그인 성공\n" + token);
  }
}
