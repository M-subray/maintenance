package zerobase.maintenance.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.ConfirmDto;
import zerobase.maintenance.service.ConfirmService;

@RequiredArgsConstructor
@RestController
public class ConfirmController {
  private final ConfirmService confirmService;


  @PostMapping("/maintenance/confirm/{maintenanceId}")
  @PreAuthorize("hasRole('PARTNER_IN_OFFICE')")
  public ResponseEntity<String> maintenanceConfirm (
      @PathVariable Long maintenanceId,
      @RequestBody @Valid ConfirmDto confirmDto) {
    confirmService.maintenanceConfirm(maintenanceId, confirmDto);
    return ResponseEntity.ok().body("유지보수 확정 완료");
  }
}
