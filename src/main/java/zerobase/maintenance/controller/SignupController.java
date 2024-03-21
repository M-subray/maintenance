package zerobase.maintenance.controller;


import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.SignupDto;
import zerobase.maintenance.service.SignupService;


@Slf4j
@RequiredArgsConstructor
@RestController
public class SignupController {

  private final SignupService signupService;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(
      @RequestBody @Valid SignupDto signupDto,
      @RequestParam("role") String role) {
    signupDto.setRole(String.valueOf(role));
    signupService.signup(signupDto);
    String message = String.format("가입 완료\n계정 : %s", signupDto.getUsername());
    return ResponseEntity.ok(message);
  }

  @PostMapping("/signup/partner/office")
  @PreAuthorize("hasRole('MANAGER')")
  public ResponseEntity<String> officePartnerSignup(
      @RequestBody @Valid SignupDto signupDto,
      @RequestParam("role") String role) {
    signupDto.setRole(String.valueOf(role));
    signupService.signup(signupDto);
    String message = String.format("가입 완료\n계정 : %s", signupDto.getUsername());
    return ResponseEntity.ok(message);
  }

  @PostMapping("/signup/partner/field")
  @PreAuthorize("hasRole('MANAGER')")
  public ResponseEntity<String> fieldPartnerSignup(
      @RequestBody @Valid SignupDto signupDto,
      @RequestParam("role") String role) {
    signupDto.setRole(String.valueOf(role));
    signupService.signup(signupDto);
    String message = String.format("가입 완료\n계정 : %s", signupDto.getUsername());
    return ResponseEntity.ok(message);
  }
}

