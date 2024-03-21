package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.dto.MaintenanceCheckForPartnerDto;
import zerobase.maintenance.dto.MaintenanceCheckForUserDto;
import zerobase.maintenance.repository.MaintenanceRepository;
import zerobase.maintenance.context.AuthenticationContext;

@Service
@RequiredArgsConstructor
public class MaintenanceCheckService {
  private final MaintenanceRepository maintenanceRepository;

  @Transactional(readOnly = true)
  public Page<MaintenanceCheckForUserDto> getAllMyMaintenanceRequest(Pageable pageable) {
    String username =
        AuthenticationContext.getAuthentication().getName();

    Page<Maintenance> maintenancePage =
        maintenanceRepository.findByAccount_Username(username, pageable);

    return maintenancePage.map(this::mapToResponseForUserDto);
  }

  private MaintenanceCheckForUserDto mapToResponseForUserDto(Maintenance maintenance) {
    return MaintenanceCheckForUserDto.builder()
        .title(maintenance.getTitle())
        .requestDateTime(maintenance.getRequestDateTime())
        .requestStatus(maintenance.getRequestStatus())
        .build();
  }

  @Transactional(readOnly = true)
  public Page<MaintenanceCheckForPartnerDto> getAllMaintenanceRequest(Pageable pageable) {
    Page<Maintenance> maintenancePage = maintenanceRepository.findAll(pageable);
    return maintenancePage.map(this::mapToResponseForPartnerDto);
  }

  private MaintenanceCheckForPartnerDto mapToResponseForPartnerDto(Maintenance maintenance) {
    return MaintenanceCheckForPartnerDto.builder()
        .title(maintenance.getTitle())
        .requestDateTime(maintenance.getRequestDateTime())
        .requestStatus(maintenance.getRequestStatus())
        .visitScheduleDateTime(maintenance.getVisitScheduleDateTime())
        .visitCompletionDateTime(maintenance.getVisitCompletionDateTime())
        .build();
  }
}
