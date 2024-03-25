package zerobase.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.maintenance.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
