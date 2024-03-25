package zerobase.maintenance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.ReviewDetailCheckDto;
import zerobase.maintenance.service.ReviewDetailCheckService;

@RestController
@RequiredArgsConstructor
public class ReviewDetailCheckController {
  private final ReviewDetailCheckService reviewDetailCheckService;

  @GetMapping("/review/check/{maintenanceId}")
  @PreAuthorize("hasRole('MANAGER')")
  private ResponseEntity<ReviewDetailCheckDto> reviewDetailCheck(
      @PathVariable Long maintenanceId) {

    return ResponseEntity.ok().body(
        reviewDetailCheckService.reviewDetailCheck(maintenanceId)
    );
  }
}
