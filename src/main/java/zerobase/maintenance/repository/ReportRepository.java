package zerobase.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.maintenance.domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
