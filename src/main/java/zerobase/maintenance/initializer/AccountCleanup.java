package zerobase.maintenance.initializer;

import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zerobase.maintenance.repository.AccountRepository;

@RequiredArgsConstructor
@Component
public class AccountCleanup {
  private final AccountRepository accountRepository;

  @PreDestroy
  public void deleteNonManagerAccounts() {
    accountRepository.deleteAllExceptManager("MANAGER");
  }
}
