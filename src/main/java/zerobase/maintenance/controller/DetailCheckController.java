package zerobase.maintenance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.DetailCheckDto;
import zerobase.maintenance.service.DetailCheckService;

@RequiredArgsConstructor
@RestController
public class DetailCheckController {
  private final DetailCheckService detailCheckService;

  @GetMapping("/maintenance/check/user/{maintenanceId}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<DetailCheckDto> getMaintenanceDetailForUser(
      @PathVariable Long maintenanceId) {
    return ResponseEntity.ok().body(
        detailCheckService.getMaintenanceDetailForUser(maintenanceId)
    );
  }

  @GetMapping("/maintenance/check/partner/{maintenanceId}")
  @PreAuthorize("hasRole('PARTNER_IN_OFFICE') or hasRole('PARTNER_ON_FIELD')")
  public ResponseEntity<DetailCheckDto> getMaintenanceDetailForPartner(
      @PathVariable Long maintenanceId) {
    return ResponseEntity.ok().body(
        detailCheckService.getMaintenanceDetailForPartner(maintenanceId)
    );
  }
}
