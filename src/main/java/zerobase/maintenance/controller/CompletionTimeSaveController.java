package zerobase.maintenance.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.service.CompletionTimeSaveService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CompletionTimeSaveController {
  private final CompletionTimeSaveService completionTimeSaveService;

  @GetMapping("/maintenance/visit-url/{maintenanceId}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<String> generateVisitURL(@PathVariable Long maintenanceId) {
    completionTimeSaveService.maintenanceComplete(maintenanceId);
    log.info("유지보수 Id: {} 방문 시간 확정완료", maintenanceId);
    return ResponseEntity.ok().body("실제 방문 시간이 확정 되었습니다.");
  }
}
