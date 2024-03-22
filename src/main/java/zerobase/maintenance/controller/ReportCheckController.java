package zerobase.maintenance.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.ReportCheckDto;
import zerobase.maintenance.service.ReportCheckService;

@RestController
@RequiredArgsConstructor

public class ReportCheckController {
  private final ReportCheckService reportCheckService;

  @GetMapping("/report/check")
  @PreAuthorize("hasRole('PARTNER_IN_OFFICE') or hasRole('PARTNER_ON_FIELD')")
  public ResponseEntity<Page<ReportCheckDto>> checkReport(Pageable pageable) {
    Page<ReportCheckDto> reportPage = reportCheckService.getAllReport(pageable);

    return ResponseEntity.ok().body(reportPage);
  }
}
