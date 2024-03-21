package zerobase.maintenance.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.CancelDto;
import zerobase.maintenance.service.CancelService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CancelController {
  private final CancelService cancelService;

  @PostMapping("/maintenance/cancel/{maintenanceId}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<String> cancel(
      @PathVariable Long maintenanceId,
      @RequestBody @Valid CancelDto cancelDto) {
    cancelService.maintenanceCancel(maintenanceId, cancelDto);
    return ResponseEntity.ok().body("취소 완료");
  }
}
