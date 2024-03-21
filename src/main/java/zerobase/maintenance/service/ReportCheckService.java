package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.domain.Report;
import zerobase.maintenance.dto.ReportCheckDto;
import zerobase.maintenance.repository.ReportRepository;

@Service
@RequiredArgsConstructor
public class ReportCheckService {
  private final ReportRepository reportRepository;

  @Transactional(readOnly = true)
  public Page<ReportCheckDto> getAllReport(Pageable pageable) {
    Page<Report> reportPage = reportRepository.findAll(pageable);
    return reportPage.map(this::mapToResponseForDto);
  }

  private ReportCheckDto mapToResponseForDto(Report report) {
    return ReportCheckDto.builder()
        .title(report.getTitle())
        .name(report.getMaintenance().getAccount().getName())
        .build();
  }
}
