package zerobase.maintenance.service;

import java.util.List;
import java.util.OptionalDouble;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.maintenance.domain.Review;
import zerobase.maintenance.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class StarReviewRatingCheckService {
  private final ReviewRepository reviewRepository;

  public Double calculateAverageStarRating(Long accountId) {
    List<Review> reviews =
        reviewRepository.findByTargetPartnerId(accountId);

    OptionalDouble average = reviews.stream()
        .mapToInt(Review::getStar)
        .average();
    return average.orElse(0.0);
  }
}
