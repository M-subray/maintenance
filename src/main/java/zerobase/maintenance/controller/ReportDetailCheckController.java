package zerobase.maintenance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.ReportDetailCheckDto;
import zerobase.maintenance.service.ReportDetailCheckService;

@RestController
@RequiredArgsConstructor
public class ReportDetailCheckController {
  private final ReportDetailCheckService reportDetailCheckService;

  @GetMapping("/report/check/{reportId}")
  @PreAuthorize("hasRole('PARTNER_IN_OFFICE') or hasRole('PARTNER_ON_FIELD')")
  public ResponseEntity<ReportDetailCheckDto> getReportDetail(
      @PathVariable Long reportId) {

    return ResponseEntity.ok().body(
        reportDetailCheckService.getReportDetail(reportId)
    );
  }
}
