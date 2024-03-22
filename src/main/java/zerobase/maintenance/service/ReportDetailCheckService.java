package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.domain.Report;
import zerobase.maintenance.dto.ReportDetailCheckDto;
import zerobase.maintenance.exception.ReportException;
import zerobase.maintenance.repository.ReportRepository;
import zerobase.maintenance.type.ErrorCode;

@Service
@RequiredArgsConstructor
public class ReportDetailCheckService {
  private final ReportRepository reportRepository;

  @Transactional(readOnly = true)
  public ReportDetailCheckDto getReportDetail (Long reportId) {
    Report getById =
        reportRepository.findById(reportId).orElseThrow(()->
        new ReportException(ErrorCode.NOT_FOUND_REPORT));

    return ReportDetailCheckDto.builder()
        .title(getById.getTitle())
        .item(getById.getMaintenance().getItem())
        .handlerPartnerInOffice(getById.getMaintenance().getHandlerPartnerInOffice())
        .handlerPartnerOnField(getById.getMaintenance().getHandlerPartnerOnField())
        .visitCompletionDateTIme(getById.getMaintenance().getVisitCompletionDateTime())
        .imagePath(getById.getImagePath())
        .reportDetail(getById.getReportDetail())
        .build();
  }
}
