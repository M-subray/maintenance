package zerobase.maintenance.initializer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import zerobase.maintenance.domain.Account;
import zerobase.maintenance.repository.AccountRepository;
import zerobase.maintenance.type.Roles;

@RequiredArgsConstructor
@Component
public class CreateManagerAccount {
  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  @PostConstruct
  public void createManagerAccount() {
    accountRepository.save(Account.builder()
        .username("MANAGER")
        .password(passwordEncoder.encode("123456"))
        .role(Roles.ROLE_MANAGER)
        .build());
  }

  @PreDestroy
  public void deleteManagerAccount() {
    accountRepository.deleteAll();
  }
}
