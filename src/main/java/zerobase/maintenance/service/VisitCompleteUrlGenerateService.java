package zerobase.maintenance.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.domain.Maintenance;
import zerobase.maintenance.exception.MaintenanceException;
import zerobase.maintenance.repository.MaintenanceRepository;
import zerobase.maintenance.type.ErrorCode;

@Service
@RequiredArgsConstructor
public class VisitCompleteUrlGenerateService {
  private final MaintenanceRepository maintenanceRepository;
  private final RedissonClient redissonClient;

  private long visitUrlTtl = 24 * 60 * 60 * 1000;

  @Value("${spring.coolsms.api.ip}")
  private String ip;

  @Transactional(readOnly = true)
  public String generateVisitURL(Long maintenanceId) {

    Maintenance maintenance =
        maintenanceRepository.findById(maintenanceId).orElseThrow(() ->
            new MaintenanceException(ErrorCode.MAINTENANCE_NOT_FOUND));

    String Url =
        String.format("http://%s:8080/maintenance/visit-url/%s",
        ip, String.valueOf(maintenance.getId()));

    redissonClient.getBucket("visit-url" + maintenanceId)
        .set(Url, visitUrlTtl, TimeUnit.HOURS);

    return Url;
  }
}
