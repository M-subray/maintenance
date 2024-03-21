package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.dto.DetailCheckDto;
import zerobase.maintenance.exception.MaintenanceException;
import zerobase.maintenance.repository.MaintenanceRepository;
import zerobase.maintenance.type.ErrorCode;
import zerobase.maintenance.utils.AuthenticationContext;

@Service
@RequiredArgsConstructor
public class DetailCheckService {
  private final MaintenanceRepository maintenanceRepository;

  @Transactional(readOnly = true)
  public DetailCheckDto getMaintenanceDetailForUser (Long maintenanceId) {

    Maintenance maintenance =
        maintenanceRepository.findById(maintenanceId).orElseThrow(() ->
            new MaintenanceException(ErrorCode.MAINTENANCE_NOT_FOUND));

    Authentication authentication =
        AuthenticationContext.getAuthentication();

    if (!authentication.getName().equals(maintenance.getAccount().getUsername())) {
      throw new MaintenanceException(ErrorCode.REQUEST_NOT_ALLOWED);
    }

    return createDetailCheckDto(maintenance);
  }

  @Transactional(readOnly = true)
  public DetailCheckDto getMaintenanceDetailForPartner (Long maintenanceId) {
    Maintenance maintenance =
        maintenanceRepository.findById(maintenanceId).orElseThrow(()->
            new MaintenanceException(ErrorCode.MAINTENANCE_NOT_FOUND));

    return createDetailCheckDto(maintenance);
  }

  private DetailCheckDto createDetailCheckDto(Maintenance maintenance) {
    return DetailCheckDto.builder()
        .name(maintenance.getAccount().getName())
        .address(maintenance.getAccount().getAddress())
        .mobile(maintenance.getAccount().getMobile())
        .title(maintenance.getTitle())
        .item(maintenance.getItem())
        .purchaseDate(maintenance.getPurchaseDate())
        .issueDescription(maintenance.getIssueDescription())
        .handlerPartnerInOffice(maintenance.getHandlerPartnerInOffice())
        .handlerPartnerOnField(maintenance.getHandlerPartnerOnField())
        .requestDateTime(maintenance.getRequestDateTime())
        .visitScheduleDateTime(maintenance.getVisitScheduleDateTime())
        .visitCompletionDateTime(maintenance.getVisitCompletionDateTime())
        .requestStatus(maintenance.getRequestStatus())
        .build();
  }
}
