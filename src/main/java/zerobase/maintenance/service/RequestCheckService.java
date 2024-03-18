package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.dto.RequestCheckForPartnerDto;
import zerobase.maintenance.dto.RequestCheckForUserDto;
import zerobase.maintenance.repository.MaintenanceRepository;
import zerobase.maintenance.utils.AuthenticationContext;

@Service
@RequiredArgsConstructor
public class RequestCheckService {
  private final MaintenanceRepository maintenanceRepository;

  @Transactional(readOnly = true)
  public Page<RequestCheckForUserDto> getAllMyMaintenanceRequest(Pageable pageable) {
    String username =
        AuthenticationContext.getAuthentication().getName();

    Page<Maintenance> maintenancePage =
        maintenanceRepository.findByAccount_Username(username, pageable);

    return maintenancePage.map(this::mapToResponseForUserDto);
  }

  private RequestCheckForUserDto mapToResponseForUserDto(Maintenance maintenance) {
    return RequestCheckForUserDto.builder()
        .title(maintenance.getTitle())
        .requestDateTime(maintenance.getRequestDateTime())
        .requestStatus(maintenance.getRequestStatus())
        .build();
  }

  @Transactional(readOnly = true)
  public Page<RequestCheckForPartnerDto> getAllMaintenanceRequest(Pageable pageable) {
    Page<Maintenance> maintenancePage = maintenanceRepository.findAll(pageable);
    return maintenancePage.map(this::mapToResponseForPartnerDto);
  }

  private RequestCheckForPartnerDto mapToResponseForPartnerDto(Maintenance maintenance) {
    return RequestCheckForPartnerDto.builder()
        .title(maintenance.getTitle())
        .requestDateTime(maintenance.getRequestDateTime())
        .requestStatus(maintenance.getRequestStatus())
        .visitScheduleDateTime(maintenance.getVisitScheduleDateTime())
        .visitCompletionDateTime(maintenance.getVisitCompletionDateTime())
        .build();
  }
}
