package zerobase.maintenance.service;

import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    DateTimeFormatter visitCompletionFormatter =
        DateTimeFormatter.ofPattern("MM월dd일 HH시mm분");
    String visitCompletionDateTime =
        getById.getMaintenance().getVisitCompletionDateTime().format(visitCompletionFormatter);


    return ReportDetailCheckDto.builder()
        .title(getById.getTitle())
        .item(getById.getMaintenance().getItem())
        .handlerPartnerInOffice(getById.getMaintenance().getHandlerPartnerInOffice())
        .handlerPartnerOnField(getById.getMaintenance().getHandlerPartnerOnField())
        .visitCompletionDateTIme(visitCompletionDateTime)
        .imagePath(getById.getImagePath())
        .reportDetail(getById.getReportDetail())
        .build();
  }
}
