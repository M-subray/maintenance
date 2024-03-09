package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zerobase.maintenance.domain.Account;
import zerobase.maintenance.dto.UserDetailsDto;
import zerobase.maintenance.exception.AccountException;
import zerobase.maintenance.repository.AccountRepository;
import zerobase.maintenance.type.ErrorCode;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

  private final AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = findByUsername(username);
    return UserDetailsDto.builder()
        .username(account.getUsername())
        .password(account.getPassword())
        .role(account.getRole())
        .build();
  }

  private Account findByUsername(String username) throws AccountException {
    return accountRepository.findByUsername(username)
        .orElseThrow(() -> new AccountException(ErrorCode.USERNAME_NOT_FOUND));
  }
}
