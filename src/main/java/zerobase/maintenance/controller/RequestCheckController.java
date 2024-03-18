package zerobase.maintenance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.RequestCheckForPartnerDto;
import zerobase.maintenance.dto.RequestCheckForUserDto;
import zerobase.maintenance.service.RequestCheckService;

@RequiredArgsConstructor
@RestController
public class RequestCheckController {
  private final RequestCheckService requestCheckService;

  @GetMapping("/maintenance/check/user")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Page<RequestCheckForUserDto>> checkForUser(
      Pageable pageable) {
    Page<RequestCheckForUserDto> maintenancePage =
        requestCheckService.getAllMyMaintenanceRequest(pageable);
    return ResponseEntity.ok().body(maintenancePage);
  }

  @GetMapping("/maintenance/check/partner")
  @PreAuthorize("hasRole('PARTNER_IN_OFFICE') or hasRole('PARTNER_ON_FIELD')")
  public ResponseEntity<Page<RequestCheckForPartnerDto>> maintenanceCheckForPartner (
      Pageable pageable) {
    Page<RequestCheckForPartnerDto> maintenancePage =
        requestCheckService.getAllMaintenanceRequest(pageable);

    return ResponseEntity.ok().body(maintenancePage);
  }
}
