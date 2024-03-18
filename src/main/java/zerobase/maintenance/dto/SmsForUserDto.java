package zerobase.maintenance.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsForUserDto {
  private Long maintenanceId;
  private String partnerName;
  private String partnerMobile;
  private LocalDateTime visitScheduleDateTIme;

  private String userMobile;
}
