package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.context.MaintenanceContext;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.domain.CancelledMaintenance;
import zerobase.maintenance.dto.CancelDto;
import zerobase.maintenance.exception.MaintenanceException;
import zerobase.maintenance.repository.MaintenanceCancelRepository;
import zerobase.maintenance.type.ErrorCode;
import zerobase.maintenance.type.Status;
import zerobase.maintenance.context.AuthenticationContext;

@Service
@RequiredArgsConstructor
public class CancelService {

  private final MaintenanceCancelRepository maintenanceCancelRepository;
  private final MaintenanceContext maintenanceContext;

  @Transactional
  public void maintenanceCancel(Long maintenanceId, CancelDto cancelDto) {
    Authentication authentication =
        AuthenticationContext.getAuthentication();

    Maintenance maintenance =
        maintenanceContext.getMaintenance(maintenanceId);

    if (!authentication.getName().equals(maintenance.getAccount().getUsername())) {
      throw new MaintenanceException(ErrorCode.REQUEST_NOT_ALLOWED);
    }

    maintenanceCancelRepository.save(CancelledMaintenance.builder()
        .maintenanceId(maintenanceId)
        .cancelReason(cancelDto.getCancelReason())
        .build());

    cancelIfReceived(maintenance);
  }

  private void cancelIfReceived(Maintenance maintenance) {
    if (maintenance.getRequestStatus() != Status.RECEIVED) {
      throw new MaintenanceException(ErrorCode.CAN_NOT_CANCEL);
    } else {
      setStatusToCanceled(maintenance);
    }
  }

  private void setStatusToCanceled(Maintenance maintenance) {
    maintenance.setRequestStatus(Status.CANCELED);
  }
}
