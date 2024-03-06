package zerobase.maintenance.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.maintenance.domain.Account;
import zerobase.maintenance.type.Roles;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByUsername(String username);
}
