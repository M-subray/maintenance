package zerobase.maintenance.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.maintenance.domain.Review;
import zerobase.maintenance.type.ReviewType;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  Review findByMaintenanceIdAndType(Long maintenanceId, ReviewType type);
  List<Review> findByTargetPartnerId(Long accountId);
}
