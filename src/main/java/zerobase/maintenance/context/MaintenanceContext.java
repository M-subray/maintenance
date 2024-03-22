package zerobase.maintenance.context;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.exception.MaintenanceException;
import zerobase.maintenance.repository.MaintenanceRepository;
import zerobase.maintenance.type.ErrorCode;

@Component
@RequiredArgsConstructor
public class MaintenanceContext {
  private final MaintenanceRepository maintenanceRepository;

  public Maintenance getMaintenance(Long maintenanceId) {
    Maintenance maintenance =
        maintenanceRepository.findById(maintenanceId).orElseThrow(()->
            new MaintenanceException(ErrorCode.MAINTENANCE_NOT_FOUND));

    return maintenance;
  }
}
