package zerobase.maintenance.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.RequestDto;
import zerobase.maintenance.service.MaintenanceRequestService;

@RestController
@RequiredArgsConstructor
public class RequestController {

  private final MaintenanceRequestService maintenanceRequestService;

  @PostMapping("/maintenance/request")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<String> request(
      @RequestBody @Valid RequestDto request) {
    maintenanceRequestService.maintenanceRequest(request);
    return ResponseEntity.ok().body("접수 완료");
  }
}
