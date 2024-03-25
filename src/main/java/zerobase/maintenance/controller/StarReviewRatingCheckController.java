package zerobase.maintenance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zerobase.maintenance.service.StarReviewRatingCheckService;

@RestController
@RequiredArgsConstructor
public class StarReviewRatingCheckController {
  private final StarReviewRatingCheckService starReviewRatingCheckService;

  @GetMapping("review/check/star-rating/{accountId}")
  @PreAuthorize("hasRole('MANAGER')")
  public ResponseEntity<Double> starReviewRatingCheck(
      @PathVariable Long accountId) {
    Double calculateAverageStarRating =
        starReviewRatingCheckService.calculateAverageStarRating(accountId);

    return ResponseEntity.ok(calculateAverageStarRating);
  }
}
