package zerobase.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.context.MaintenanceContext;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.domain.Review;
import zerobase.maintenance.dto.ReviewDetailCheckDto;
import zerobase.maintenance.repository.ReviewRepository;
import zerobase.maintenance.type.ReviewType;

@Service
@RequiredArgsConstructor
public class ReviewDetailCheckService {
  private final MaintenanceContext maintenanceContext;
  private final ReviewRepository reviewRepository;

  @Transactional(readOnly = true)
  public ReviewDetailCheckDto reviewDetailCheck(Long maintenanceId) {

    return createDetailCheckDto(maintenanceId);
  }
  private ReviewDetailCheckDto createDetailCheckDto(Long maintenanceId) {
    maintenanceContext.getMaintenance(maintenanceId);

    Review officePartnerReview = findByMaintenanceIdAndType(maintenanceId, ReviewType.OFFICE);
    Review fieldPartnerReview = findByMaintenanceIdAndType(maintenanceId, ReviewType.FIELD);

    return ReviewDetailCheckDto.builder()
        .maintenanceId(officePartnerReview.getMaintenance().getId())
        .reviewForPartnerInOffice(officePartnerReview.getReview())
        .starForPartnerInOffice(officePartnerReview.getStar())
        .reviewForPartnerOnField(fieldPartnerReview.getReview())
        .starForPartnerOnField(fieldPartnerReview.getStar())
        .build();
  }

  private Review findByMaintenanceIdAndType(Long maintenanceId, ReviewType reviewType) {
    return reviewRepository.findByMaintenanceIdAndType(maintenanceId, reviewType);
  }
}
