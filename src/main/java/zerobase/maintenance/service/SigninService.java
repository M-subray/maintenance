package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.domain.Account;
import zerobase.maintenance.exception.AccountException;
import zerobase.maintenance.repository.AccountRepository;
import zerobase.maintenance.type.ErrorCode;
import zerobase.maintenance.type.Roles;

@Service
@RequiredArgsConstructor
public class SigninService {

  private final PasswordEncoder passwordEncoder;
  private final AccountRepository accountRepository;

  @Transactional(readOnly = true)
  public Account signin(String username, String password) {
    Account savedAccount = accountRepository.findByUsername(username)
        .orElseThrow(() -> new AccountException(ErrorCode.USERNAME_NOT_FOUND));

    if (isPasswordMatch(password, savedAccount.getPassword())) {
      return savedAccount;
    } else {
      throw new AccountException(ErrorCode.WRONG_PASSWORD);
    }
  }

  private boolean isPasswordMatch(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }

  public Roles getRole(String username) {
    Account account = accountRepository.findByUsername(username).orElseThrow(() ->
        new AccountException(ErrorCode.USERNAME_NOT_FOUND));

    return account.getRole();
  }
}
