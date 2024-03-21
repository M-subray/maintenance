package zerobase.maintenance.context;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import zerobase.maintenance.exception.AccountException;
import zerobase.maintenance.type.ErrorCode;

public class AuthenticationContext {

  public static Authentication getAuthentication() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!authentication.isAuthenticated()) {
      throw new AccountException(ErrorCode.SIGNIN_TIME_OUT);
    }

    return authentication;
  }
}
