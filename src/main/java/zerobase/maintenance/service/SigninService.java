package zerobase.maintenance.service;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.maintenance.domain.Account;
import zerobase.maintenance.exception.AccountException;
import zerobase.maintenance.repository.AccountRepository;
import zerobase.maintenance.type.ErrorCode;

@Service
@RequiredArgsConstructor
public class SigninService {

  private final PasswordEncoder passwordEncoder;
  private final AccountRepository accountRepository;

  @Transactional
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
}
