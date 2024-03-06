package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zerobase.maintenance.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

  private final AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.accountRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("해당 유저는 존재하지 않습니다."));
  }
}
