package zerobase.maintenance.service;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.maintenance.domain.Account;
import zerobase.maintenance.dto.SignupDto;
import zerobase.maintenance.exception.AccountException;
import zerobase.maintenance.repository.AccountRepository;
import zerobase.maintenance.type.ErrorCode;
import zerobase.maintenance.type.Roles;

@Service
@RequiredArgsConstructor
public class SignupService {

  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void signup(SignupDto signupDto) {
    checkUsername(signupDto.getUsername());
    accountRepository.save(Account.builder()
        .username(signupDto.getUsername())
        .password(passwordEncoder.encode(signupDto.getPassword()))
        .name(signupDto.getName())
        .address(signupDto.getAddress())
        .mobile(signupDto.getMobile())
        .mail(signupDto.getMail())
        .role(Roles.valueOf(signupDto.getRole()))
        .build());
  }

  private void checkUsername(String username) {
    if (accountRepository.findByUsername(username).isPresent()) {
      throw new AccountException(ErrorCode.ALREADY_USERNAME);
    }
  }
}
