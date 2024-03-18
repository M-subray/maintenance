package zerobase.maintenance.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.domain.Account;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.dto.RequestDto;
import zerobase.maintenance.exception.AccountException;
import zerobase.maintenance.repository.AccountRepository;
import zerobase.maintenance.repository.MaintenanceRepository;
import zerobase.maintenance.type.ErrorCode;
import zerobase.maintenance.type.Status;
import zerobase.maintenance.utils.AuthenticationContext;

@Service
@RequiredArgsConstructor
public class MaintenanceRequestService {
  private final AccountRepository accountRepository;
  private final MaintenanceRepository maintenanceRepository;

  @Transactional
  public void maintenanceRequest(RequestDto request) {
    Authentication authentication =
        AuthenticationContext.getAuthentication();

    Account getByUsername =
        accountRepository.findByUsername(authentication.getName()).orElseThrow(()->
            new AccountException(ErrorCode.USERNAME_NOT_FOUND));

    maintenanceRepository.save(Maintenance.builder()
        .account(getByUsername)
        .title(request.getTitle())
        .item(request.getItem())
        .purchaseDate(request.getPurchaseDate())
        .issueDescription(request.getIssueDescription())
        .requestStatus(Status.RECEIVED)
        .build());
  }
}
