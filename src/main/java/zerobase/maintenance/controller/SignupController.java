package zerobase.maintenance.controller;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.SignupDto;
import zerobase.maintenance.security.TokenProvider;
import zerobase.maintenance.service.SignupService;
import zerobase.maintenance.type.Roles;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SignupController {

  private final SignupService signupService;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(
      @RequestBody @Valid SignupDto.Request request,
      @RequestParam("role") String role) {
    request.setRole(String.valueOf(role));
    signupService.signup(request);
    String message = String.format("가입 완료\n계정 : %s", request.getUsername());
    return ResponseEntity.ok(message);
  }

  @PostMapping("/signup/partner/office")
  @PreAuthorize("hasRole('MANAGER')")
  public ResponseEntity<String> officePartnerSignup(
      @RequestBody @Valid SignupDto.Request request,
      @RequestParam("role") String role) {
    request.setRole(String.valueOf(role));
    signupService.signup(request);
    String message = String.format("가입 완료\n계정 : %s", request.getUsername());
    return ResponseEntity.ok(message);
  }

  @PostMapping("/signup/partner/field")
  @PreAuthorize("hasRole('MANAGER')")
  public ResponseEntity<String> fieldPartnerSignup(
      @RequestBody @Valid SignupDto.Request request,
      @RequestParam("role") String role) {
    request.setRole(String.valueOf(role));
    signupService.signup(request);
    String message = String.format("가입 완료\n계정 : %s", request.getUsername());
    return ResponseEntity.ok(message);
  }
}

