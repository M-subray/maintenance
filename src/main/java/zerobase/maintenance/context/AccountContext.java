package zerobase.maintenance.context;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zerobase.maintenance.domain.Account;
import zerobase.maintenance.exception.AccountException;
import zerobase.maintenance.repository.AccountRepository;
import zerobase.maintenance.type.ErrorCode;

@Component
@RequiredArgsConstructor
public class AccountContext {
  private final AccountRepository accountRepository;

  public Account getAccount(String username) {
    Account account = accountRepository.findByUsername(username).orElseThrow(()->
        new AccountException(ErrorCode.USERNAME_NOT_FOUND));

    return account;
  }
}
