package zerobase.maintenance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.MaintenanceDetailCheckDto;
import zerobase.maintenance.service.MaintenanceDetailCheckService;

@RequiredArgsConstructor
@RestController
public class MaintenanceDetailCheckController {
  private final MaintenanceDetailCheckService maintenanceDetailCheckService;

  @GetMapping("/maintenance/check/user/{maintenanceId}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<MaintenanceDetailCheckDto> getMaintenanceDetailForUser(
      @PathVariable Long maintenanceId) {

    return ResponseEntity.ok().body(
        maintenanceDetailCheckService.getMaintenanceDetailForUser(maintenanceId)
    );
  }

  @GetMapping("/maintenance/check/partner/{maintenanceId}")
  @PreAuthorize("hasRole('PARTNER_IN_OFFICE') or hasRole('PARTNER_ON_FIELD')")
  public ResponseEntity<MaintenanceDetailCheckDto> getMaintenanceDetailForPartner(
      @PathVariable Long maintenanceId) {

    return ResponseEntity.ok().body(
        maintenanceDetailCheckService.getMaintenanceDetailForPartner(maintenanceId)
    );
  }
}
