package zerobase.maintenance.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.context.MaintenanceContext;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.exception.UrlException;
import zerobase.maintenance.type.ErrorCode;

@Service
@RequiredArgsConstructor
public class CompletionTimeSaveService {
  private final MaintenanceContext maintenanceContext;
  private final RedissonClient redissonClient;

  @Transactional
  public void maintenanceComplete(Long maintenanceId) {
    long ttl =
        redissonClient.getBucket("visit-url" + maintenanceId).remainTimeToLive();
    if (ttl == -2) {
      throw new UrlException(ErrorCode.NOT_GENERATE_URL);
    } else if (ttl == -1) {
      throw new UrlException(ErrorCode.URL_EXPIRED);
    } else {
      Maintenance maintenance =
          maintenanceContext.getMaintenance(maintenanceId);
      maintenance.setVisitCompletionDateTime(LocalDateTime.now());
    }
  }
}
