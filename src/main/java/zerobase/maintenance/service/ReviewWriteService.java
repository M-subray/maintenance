package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.context.AccountContext;
import zerobase.maintenance.context.AuthenticationContext;
import zerobase.maintenance.context.MaintenanceContext;
import zerobase.maintenance.domain.Account;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.domain.Review;
import zerobase.maintenance.dto.ReviewWriteDto;
import zerobase.maintenance.exception.AccountException;
import zerobase.maintenance.exception.MaintenanceException;
import zerobase.maintenance.repository.ReviewRepository;
import zerobase.maintenance.type.ErrorCode;
import zerobase.maintenance.type.Status;

@Service
@RequiredArgsConstructor
public class ReviewWriteService {
  private final ReviewRepository reviewRepository;
  private final MaintenanceContext maintenanceContext;
  private final AccountContext accountContext;

  @Transactional
  public void write(Long maintenanceId, ReviewWriteDto reviewWriteDto) {
    Authentication authentication =
        AuthenticationContext.getAuthentication();

    Maintenance maintenance =
        maintenanceContext.getMaintenance(maintenanceId);

    String culUsername = authentication.getName();
    String maintenanceUsername = maintenance.getAccount().getUsername();

    checkUsernameAuthorization(culUsername, maintenanceUsername);
    checkCompletedStatus(maintenance);

    generateReviewForPartnerInOffice(maintenance, reviewWriteDto);
    generateReviewForPartnerOnField(maintenance, reviewWriteDto);
  }

  private void checkUsernameAuthorization(String culUsername, String maintenanceUsername) {
    if (!culUsername.equals(maintenanceUsername)) {
      throw new AccountException(ErrorCode.REQUEST_NOT_ALLOWED);
    }
  }

  private void checkCompletedStatus(Maintenance maintenance) {
    if (maintenance.getRequestStatus() != Status.COMPLETED) {
      throw new MaintenanceException(ErrorCode.CAN_NOT_LEAVE_REVIEW);
    }
  }

  private void generateReviewForPartnerInOffice(
      Maintenance maintenance, ReviewWriteDto reviewWriteDto) {
    Long accountId =
        getAccountId(maintenance.getHandlerPartnerInOffice());

    reviewRepository.save(Review.builder()
        .maintenance(maintenance)
        .targetPartnerId(accountId)
        .star(reviewWriteDto.getStarForPartnerInOffice().getValue())
        .review(reviewWriteDto.getReviewForPartnerInOffice())
        .build());
  }

  private void generateReviewForPartnerOnField(
      Maintenance maintenance, ReviewWriteDto reviewWriteDto) {
    Long accountId =
        getAccountId(maintenance.getHandlerPartnerOnField());

    reviewRepository.save(Review.builder()
        .maintenance(maintenance)
        .targetPartnerId(accountId)
        .star(reviewWriteDto.getStarForPartnerOnField().getValue())
        .review(reviewWriteDto.getReviewForPartnerOnField())
        .build());
  }

  private Long getAccountId (String partnerUsername) {
    Account account =
        accountContext.getAccount(partnerUsername);

    return account.getId();
  }
}
