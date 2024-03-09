package zerobase.maintenance.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByUsername(String username);
  @Modifying
  @Transactional
  @Query("DELETE FROM Account a WHERE a.username <> :username")
  void deleteAllExceptManager(@Param("username") String username);
}
