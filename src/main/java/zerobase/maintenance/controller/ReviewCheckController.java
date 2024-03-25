package zerobase.maintenance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.dto.ReviewCheckDto;
import zerobase.maintenance.service.ReviewCheckService;

@RestController
@RequiredArgsConstructor
public class ReviewCheckController {
  private final ReviewCheckService reviewCheckService;

  @GetMapping("/review/check")
  @PreAuthorize("hasRole('MANAGER')")
  private ResponseEntity<Page<ReviewCheckDto>> reviewCheck(
      Pageable pageable) {
    Page<ReviewCheckDto> reviewPage =
        reviewCheckService.getAllReview(pageable);

    return ResponseEntity.ok().body(reviewPage);
  }
}
