package zerobase.maintenance.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.maintenance.context.MaintenanceContext;
import zerobase.maintenance.domain.Maintenance;

@Service
@RequiredArgsConstructor
public class VisitCompleteUrlGenerateService {
  private final MaintenanceContext maintenanceContext;
  private final RedissonClient redissonClient;

  private long visitUrlTtl = 24 * 60 * 60 * 1000;

  @Value("${spring.coolsms.api.url}")
  private String url;

  @Transactional(readOnly = true)
  public String generateVisitURL(Long maintenanceId) {

    Maintenance maintenance =
        maintenanceContext.getMaintenance(maintenanceId);

    String Url =
        url + String.valueOf(maintenance.getId());

//    String Url =
//        String.format("http://%s:8080/maintenance/visit-url/%s",
//        ip, String.valueOf(maintenance.getId()));

    redissonClient.getBucket("visit-url" + maintenanceId)
        .set(Url, visitUrlTtl, TimeUnit.HOURS);

    return Url;
  }
}
