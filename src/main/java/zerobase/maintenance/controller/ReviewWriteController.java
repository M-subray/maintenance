package zerobase.maintenance.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.ReviewWriteDto;
import zerobase.maintenance.service.ReviewWriteService;

@RestController
@RequiredArgsConstructor
public class ReviewWriteController {
  private final ReviewWriteService reviewWriteService;

  @PostMapping("/maintenance/review/{maintenanceId}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<String> write (
      @PathVariable Long maintenanceId,
      @RequestBody @Valid ReviewWriteDto reviewWriteDto) {
    reviewWriteService.write(maintenanceId, reviewWriteDto);

    return ResponseEntity.ok().body("리뷰를 남겨 주셔서 감사합니다.");
  }
}