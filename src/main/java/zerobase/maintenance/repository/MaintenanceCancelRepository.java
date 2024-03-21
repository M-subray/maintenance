package zerobase.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.maintenance.domain.CancelledMaintenance;

@Repository
public interface MaintenanceCancelRepository extends JpaRepository<CancelledMaintenance, Long> {
}
