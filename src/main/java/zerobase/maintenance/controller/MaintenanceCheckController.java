package zerobase.maintenance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.MaintenanceCheckForPartnerDto;
import zerobase.maintenance.dto.MaintenanceCheckForUserDto;
import zerobase.maintenance.service.MaintenanceCheckService;

@RequiredArgsConstructor
@RestController
public class MaintenanceCheckController {
  private final MaintenanceCheckService maintenanceCheckService;

  @GetMapping("/maintenance/check/user")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Page<MaintenanceCheckForUserDto>> checkForUser(
      Pageable pageable) {
    Page<MaintenanceCheckForUserDto> maintenancePage =
        maintenanceCheckService.getAllMyMaintenanceRequest(pageable);
    return ResponseEntity.ok().body(maintenancePage);
  }

  @GetMapping("/maintenance/check/partner")
  @PreAuthorize("hasRole('PARTNER_IN_OFFICE') or hasRole('PARTNER_ON_FIELD')")
  public ResponseEntity<Page<MaintenanceCheckForPartnerDto>> maintenanceCheckForPartner (
      Pageable pageable) {
    Page<MaintenanceCheckForPartnerDto> maintenancePage =
        maintenanceCheckService.getAllMaintenanceRequest(pageable);

    return ResponseEntity.ok().body(maintenancePage);
  }
}
