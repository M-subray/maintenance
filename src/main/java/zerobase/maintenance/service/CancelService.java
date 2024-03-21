package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.domain.CancelledMaintenance;
import zerobase.maintenance.dto.CancelDto;
import zerobase.maintenance.exception.MaintenanceException;
import zerobase.maintenance.repository.MaintenanceCancelRepository;
import zerobase.maintenance.repository.MaintenanceRepository;
import zerobase.maintenance.type.ErrorCode;
import zerobase.maintenance.type.Status;
import zerobase.maintenance.utils.AuthenticationContext;

@Service
@RequiredArgsConstructor
public class CancelService {

  private final MaintenanceRepository maintenanceRepository;
  private final MaintenanceCancelRepository maintenanceCancelRepository;

  @Transactional
  public void maintenanceCancel(Long maintenanceId, CancelDto cancelDto) {
    Maintenance maintenance =
        maintenanceRepository.findById(maintenanceId).orElseThrow(()->
            new MaintenanceException(ErrorCode.MAINTENANCE_NOT_FOUND));

    cancelIfReceived(maintenance);

    Authentication authentication =
        AuthenticationContext.getAuthentication();

    if (!authentication.getName().equals(maintenance.getAccount().getUsername())) {
      throw new MaintenanceException(ErrorCode.REQUEST_NOT_ALLOWED);
    }

    maintenanceCancelRepository.save(CancelledMaintenance.builder()
        .maintenanceId(maintenanceId)
        .cancelReason(cancelDto.getCancelReason())
        .build());
  }

  private void cancelIfReceived(Maintenance maintenance) {
    if (maintenance.getRequestStatus() != Status.RECEIVED) {
      throw new MaintenanceException(ErrorCode.STATUS_ERROR);
    } else {
      setStatusToCanceled(maintenance);
    }
  }

  private void setStatusToCanceled(Maintenance maintenance) {
    maintenance.setRequestStatus(Status.CANCELED);
  }
}
