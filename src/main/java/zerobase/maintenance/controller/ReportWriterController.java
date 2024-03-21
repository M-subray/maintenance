package zerobase.maintenance.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.ReportWriterDto;
import zerobase.maintenance.service.ReportWriterService;

@RestController
@RequiredArgsConstructor
public class ReportWriterController {
  private final ReportWriterService reportWriterService;

  @PostMapping("/maintenance/report/write/{maintenanceId}")
  @PreAuthorize("hasRole('PARTNER_ON_FIELD')")
  public ResponseEntity<String> write (
      @PathVariable Long maintenanceId,
      @ModelAttribute("reportWriterDto") @Valid ReportWriterDto reportWriterDto) {
    reportWriterService.write(maintenanceId, reportWriterDto);

    return ResponseEntity.ok().body("리포트 입력 완료");
  }
}
