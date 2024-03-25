package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.context.AccountContext;
import zerobase.maintenance.domain.Review;
import zerobase.maintenance.dto.ReviewCheckDto;
import zerobase.maintenance.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewCheckService {
  private final ReviewRepository reviewRepository;
  private final AccountContext accountContext;

  @Transactional(readOnly = true)
  public Page<ReviewCheckDto> getAllReview(Pageable pageable) {
    Page<Review> reviewPage = reviewRepository.findAll(pageable);
    return reviewPage.map(this::mapToResponseReviewCheckDto);
  }

  private ReviewCheckDto mapToResponseReviewCheckDto(Review review) {

    return ReviewCheckDto.builder()
        .maintenanceId(review.getMaintenance().getId())
        .maintenanceTitle(review.getMaintenance().getTitle())
        .handlerPartner(getHandlerPartner(review))
        .build();
  }

  private String getHandlerPartner (Review review) {
    return accountContext.getAccount(review.getTargetPartnerId()).getName();
  }
}
