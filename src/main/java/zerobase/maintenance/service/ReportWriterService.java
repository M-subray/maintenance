package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zerobase.maintenance.context.MaintenanceContext;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.domain.Report;
import zerobase.maintenance.dto.ReportWriterDto;
import zerobase.maintenance.exception.AccountException;
import zerobase.maintenance.exception.MaintenanceException;
import zerobase.maintenance.repository.MaintenanceRepository;
import zerobase.maintenance.repository.ReportRepository;
import zerobase.maintenance.type.ErrorCode;
import zerobase.maintenance.type.Status;
import zerobase.maintenance.context.AuthenticationContext;

@Service
@RequiredArgsConstructor
public class ReportWriterService {
  private final MaintenanceRepository maintenanceRepository;
  private final ReportRepository reportRepository;
  private final StorageService storageService;
  private final MaintenanceContext maintenanceContext;

  public void write(Long maintenanceId, ReportWriterDto reportWriterDto) {
    Authentication authentication = AuthenticationContext.getAuthentication();

    Maintenance maintenance =
        maintenanceContext.getMaintenance(maintenanceId);

    if (!authentication.getName().equals(maintenance.getHandlerPartnerOnField())) {
      throw new AccountException(ErrorCode.REQUEST_NOT_ALLOWED);
    }

    MultipartFile image = reportWriterDto.getImage();
    String imagePath = null;

    if (image != null && !image.isEmpty()) {
      imagePath = storageService.store(image);
    }

    reportRepository.save(Report.builder()
        .maintenance(maintenance)
        .title(reportWriterDto.getTitle())
        .reportDetail(reportWriterDto.getReportDetail())
        .imagePath(imagePath)
        .build());

    completeIfConfirm(maintenance);
  }

  private void completeIfConfirm(Maintenance maintenance) {
    if (maintenance.getRequestStatus() != Status.CONFIRMED) {
      throw new MaintenanceException(ErrorCode.CAN_NOT_COMPLETE);
    } else {
      setStatusToComplete(maintenance);
    }
  }

  private void setStatusToComplete(Maintenance maintenance) {
    maintenance.setRequestStatus(Status.COMPLETED);
    maintenanceRepository.save(maintenance);
  }
}
